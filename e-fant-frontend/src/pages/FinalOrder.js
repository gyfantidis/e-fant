import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";



function FinalOrder(props) {

    const [user, setUser] = useState({});
    const [restaurant, setRestaurant] = useState({});
    const [items, setItems] = useState([]);
    const [userConfirmed, setUserConfirmed] = useState(false); // State to track user's choice
    const [orderCanceled, setOrderCanceled] = useState(false); // State to track order cancellation




    useEffect(() => {
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

    const handleConfirm = () => {
        setUserConfirmed(true);
    };

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
                    <div >
                        <h6 className="tm-list-item">Address
                            : {user.addresses[0].address} - {user.addresses[0].addressNumber} - {user.addresses[0].floor}</h6>
                        <h6 className="tm-list-item">City : {user.addresses[0].city} - Zip
                            code: {user.addresses[0].zipCode}</h6>
                    </div>
                ) : (
                    <p>No address available.</p>
                )}

            </div>

            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Order From</h2>
                <h6 className="tm-list-item">Restaurant :  {restaurant.name}</h6>
                <h6 className="tm-list-item">Address :  {restaurant.address}</h6>
                <h6 className="tm-list-item">Phone:  {restaurant.phone}</h6>


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
                        <span className="tm-item-price ">Total: ${items.reduce((total, item) => total + item.price, 0).toFixed(2)}</span>
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
                    </button>{" "}
                    <button onClick={handleCancel} className="tm-btn-primary">
                        No
                    </button>
                </div>
            )}


        </div>
    );
}

export default FinalOrder;