import React, {useEffect, useState} from "react";
import OrderItem from "./OrderItem";
import OrderButton from "./OrderButton"; // Import your OrderItem component

function OrderItems(props) {


    const [items, setItems] = useState([]);
    let authCredentials = localStorage.getItem("authToken");
    const [itemKey, setItemKey] = useState(0); // Add a state for the key

    useEffect(() => {


        if (props.item.itemId !== "0") {
            const apiUrl = `http://52.206.235.17:8080/menu/items/${props.item.itemId}`;

            fetch(apiUrl, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Basic " + authCredentials
                }
            })
                .then(response => response.json())
                .then(data => {
                    setItems(prevItems => [...prevItems, data]);
                    console.log("Data type:", typeof data);
                    console.log("Data:", data);
                });
        } else {
            // Clear the item state when itemId is "0"
            setItems([]);
        }
    }, [itemKey]);


    const calculateTotalPrice = () => {
        return items.reduce((total, item) => total + item.price, 0);
    };


    const handleDeleteItem = (index) => {
        const itemIndex = index;

        if (itemIndex !== -1) {
            const updatedItems = [...items];
            updatedItems.splice(itemIndex, 1);
            setItems(updatedItems);
        }
    };

    // When props.item changes, update the key to trigger useEffect
    useEffect(() => {
        setItemKey(prevKey => prevKey + 1);
    }, [props.item]);


    return (
        <div className="tm-black-bg tm-list-item-text restaurant-header">
            <h3 className="tm-text-primary tm-list-item-name"><i className="fas fa-utensils"></i>
                <span
                    className="tm-list-item-price"><i className="fas fa-dollar-sign"></i>
                </span></h3>
            <div className="">

            {items.map((item, index) => {
                return <OrderItem item={item} index={index} key={index} onDelete={handleDeleteItem}/>;
            })}
            </div>

            <h3 className="tm-text-primary tm-list-item-name">Total Price:<span
                className="tm-list-item-price">${calculateTotalPrice().toFixed(2)}</span></h3>
            <br/>
            <br/>
            <OrderButton restaurant={props.restaurant} items={items}/>


        </div>


    );
}

export default OrderItems;
