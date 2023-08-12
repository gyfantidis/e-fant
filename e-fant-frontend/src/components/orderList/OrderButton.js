import React, {useState} from "react";
import {Link} from "react-router-dom";

function OrderButton(props) {

    const handleConfirmOrder = () => {
        if (props.items.length === 0) {
            // Show browser alert message
            window.alert("Please add an item before confirming the order.");
        } else {
            // Open a new popup window with the FinalOrder component
            const finalOrderWindow = window.open("", "_blank", "width=800,height=600");

            // Pass data to the popup window (you can use query parameters or localStorage)
            finalOrderWindow.localStorage.setItem(
                "restaurant",
                JSON.stringify(props.restaurant)
            );
            finalOrderWindow.localStorage.setItem("items", JSON.stringify(props.items));

            // Load the FinalOrder component into the popup window
            finalOrderWindow.location.href = "/final-order";
        }
    };


    return (
        <div>
            <h3 className="tm-text-primary tm-list-item-name">
                <button
                    className="tm-page-nav-item tm-btn-primary tm-align-right"
                    onClick={handleConfirmOrder}
                >
                    Make the Order
                </button>
            </h3>
            <br />
        </div>
    )
        ;
}

export default OrderButton;