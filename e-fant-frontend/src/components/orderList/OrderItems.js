import React, {useEffect, useState} from "react";
import OrderItem from "./OrderItem";
import Item from "../menuItems/Item";
import OrderButton from "./OrderButton"; // Import your OrderItem component

function OrderItems(props) {


    const[items, setItems] = useState([]);
    let authCredentials = localStorage.getItem("authToken");

    useEffect(() => {
        if (props.item.itemId !== "0") {
            const apiUrl = `http://localhost:8080/menu/items/${props.item.itemId}`;

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
    }, [props.item.itemId, authCredentials]);


    const calculateTotalPrice = () => {
        return items.reduce((total, item) => total + item.price, 0);
    };



    const handleDeleteItem = (itemId) => {
        const itemIndex = items.findIndex(item => item.itemId === itemId);

        if (itemIndex !== -1) {
            const updatedItems = [...items];
            updatedItems.splice(itemIndex, 1);
            setItems(updatedItems);
        }
    };





    return (
        <div>
            <h3 className="tm-text-primary tm-list-item-name"><i className="fas fa-utensils"></i>
                <span
                    className="tm-list-item-price"><i className="fas fa-dollar-sign"></i>
                </span></h3>


            {items.map((item) => {
                return <OrderItem item={item} key={item.itemId} onDelete={handleDeleteItem}/>;
            })}

            <h3 className="tm-text-primary tm-list-item-name">Total Price:<span
                className="tm-list-item-price">${calculateTotalPrice().toFixed(2)}</span></h3>
            <br/>
            <br/>
            <OrderButton restaurant={props.restaurant} items={items}/>


        </div>


    );
}

export default OrderItems;
