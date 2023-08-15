import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";


function FinalOrder(props) {

    const [user, setUser] = useState({});
    const [restaurant, setRestaurant] = useState({});
    const [items, setItems] = useState([]);
    const [userConfirmed, setUserConfirmed] = useState(false); // State to track user's choice
    const [orderCanceled, setOrderCanceled] = useState(false); // State to track order cancellation
    const [registrationStatus, setRegistrationStatus] = useState("initial"); // "initial", "success", "failure"
    const [authToken, setAuthToken] = useState("");
    const now = new Date(); // Get current date and time


    useEffect(() => {
        setAuthToken(localStorage.getItem("authToken"));

        const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));
        if (loggedInUser) {
            setUser(loggedInUser);
        }

        const storedRestaurant = JSON.parse(localStorage.getItem("restaurant"));
        if (storedRestaurant) {
            setRestaurant(storedRestaurant);
        }

        const storedItems = JSON.parse(localStorage.getItem("items"));
        if (storedItems) {
            setItems(storedItems);
        }

    }, []);


    const handleConfirm = async (event) => {
        event.preventDefault();
        setUserConfirmed(true);

        const orderItems = items.map((item) => {
            return {
                itemId: item.id,
                quantity: 1, // Assuming quantity is 1 for each item
            };
        });
        const totalAmount = items.reduce((total, item) => total + item.price, 0);

        const orderData = {
            userId: user.id,
            restaurantId: restaurant.restaurantId,
            deliveryAddressId: user.addresses && user.addresses.length > 0 ? user.addresses[0].addressId : null,
            totalAmount: totalAmount,
            orderDate: now,
        };


        try {
            // Send the updated user profile to the server
            const orderResponse = await fetch(`http://localhost:8080/orders`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Basic " + authToken
                },
                body: JSON.stringify(orderData), // Send the user object as JSON
            })


            if (orderResponse.ok) {
                // Successfully created the order
                console.log("Order registered successfully.");

                // Parse the response to get the created order's ID
                const createdOrder = await orderResponse.json();
                const orderId = createdOrder.orderId;


                // Now create the order items
                const orderItemsData = items.map((item) => {
                    return {
                        itemId: item.itemId,
                        orderId: orderId,
                        quantity: 1, // Assuming quantity is 1 for each item
                    };
                });





                for (const orderItemData of orderItemsData) {
                    const orderItemsResponse = await fetch(`http://localhost:8080/orders/items`, {
                        method: 'POST',
                        headers: {
                            "Content-Type": "application/json",
                            "Authorization": "Basic " + authToken
                        },
                        body: JSON.stringify(orderItemData),
                    });


                    console.log("66666666666666666666666666666666666666");
                    console.log(orderId);
                    console.log(orderItemsData);

                    if (orderItemsResponse.ok) {
                        console.log("Order items registered successfully.");
                    } else {
                        console.error("Order items registration failed.");
                        alert("Order registration failed.");
                        setRegistrationStatus("failure");
                        return;
                    }
                }
                console.log("All order items registered successfully.");
                alert("Order registered successfully.");
                setRegistrationStatus("success");

            } else {
                console.error("Order registration failed.");
                alert("Order registration failed.");
                setRegistrationStatus("failure");
            }
        } catch (error) {
            console.error("An error occurred:", error);
            alert("An error occurred:", error);
            setRegistrationStatus("failure");
        }
    }

        const handleCancel = () => {
            // Perform any necessary action on cancel
            setUserConfirmed(false);
            setOrderCanceled(true);
        };



    return (
        <div id="contact" className="tm-page-content">


            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Order To</h2>
                <h6 className="tm-list-item">Name : {user.firstName} {user.lastName}</h6>
                <h6 className="tm-list-item">Phone : {user.phone}</h6>
                {user.addresses && user.addresses.length > 0 ? (
                    <div>
                        <h6 className="tm-list-item">Address
                            : {user.addresses[0].address} - {user.addresses[0].addressNumber} - {user.addresses[0].floor}</h6>
                        <h6 className="tm-list-item">City : {user.addresses[0].city} - Zip
                            code: {user.addresses[0].zipCode}</h6>
                    </div>
                ) : (
                    <div>
                        <p>No address available </p>
                            <button className="tm-page-nav-item tm-btn-primary ">

                                <Link to={`/create-address`}>
                                    Create Address
                                </Link>
                            </button>

                    </div>
                )}

            </div>

            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Order From</h2>
                <h6 className="tm-list-item">Restaurant : {restaurant.name}</h6>
                <h6 className="tm-list-item">Address : {restaurant.address}</h6>
                <h6 className="tm-list-item">Phone: {restaurant.phone}</h6>


            </div>

            <div className="tm-black-bg tm-contact-text-container">
                <h2 className="tm-text-primary">Items</h2>
                {/*<div className="tm-item-list">*/}

                {items.map((item, index) => (
                    <div className="tm-list-item-text tm-data-row" key={index}>
                        <span className="tm-column-index">{index + 1}) </span>
                        <span className="tm-item-name ">{item.name}</span>
                        <span className="tm-item-price "> : ${item.price.toFixed(2)}</span>
                    </div>
                ))}
                <div className="tm-list-item-text tm-footer-row">
                    <span className="tm-column-index"></span>
                    <span className="tm-item-name "></span>
                    <span
                        className="tm-item-price ">Total: ${items.reduce((total, item) => total + item.price, 0).toFixed(2)}</span>
                </div>
                {/*</div>*/}
            </div>

            {orderCanceled ? ( // Show order canceled message and return button
                <div className="tm-black-bg tm-contact-text-container">
                    <h3>Your order has been canceled. Return to the menu.</h3>
                    <button className="tm-page-nav-item tm-btn-primary" onClick={() => window.close()}>
                        Back to Menu
                    </button>
                </div>
            ) : userConfirmed ? ( // Show user's choice confirmation
                <div className="tm-black-bg tm-contact-text-container">
                    <h3>Your order has been confirmed. Enjoy your meal!</h3>
                    <button className="tm-page-nav-item tm-btn-primary" onClick={() => window.close()}>
                        Back to Menu
                    </button>
                </div>
            ) : (
                // Show "Yes" or "No" buttons for confirmation
                <div className="tm-black-bg tm-contact-text-container">
                    <h3>Confirm your order?</h3>
                    <button onClick={handleConfirm} className="tm-btn-primary">
                        Yes
                    </button>
                    {" "}
                    <button onClick={handleCancel} className="tm-btn-primary">
                        No
                    </button>
                </div>
            )}


        </div>
    );

}

export default FinalOrder;