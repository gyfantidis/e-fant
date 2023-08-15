import React, {useEffect, useState} from "react";
import Item from "./Item";



function Items(props) {


    let restaurantId = props.restaurant;

    let [items, setItems] = useState([]);


    let authCredentials = localStorage.getItem("authToken");



    useEffect(() => {
        fetch(`http://localhost:8080/restaurants/items/${restaurantId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }

        })
            .then(response => response.json())
            .then(data => {
                setItems(data);
                console.log("Data type:", typeof data); // Check the data type
                console.log("Data:", data); // Check the data content

            });
    }, []);



    return (

        <div className="tm-list">
            {items.map((item) => {
                return <Item restaurant = {restaurantId} item={item} key={item.itemId}/>;
            })}
        </div>


    );
}


export default Items;