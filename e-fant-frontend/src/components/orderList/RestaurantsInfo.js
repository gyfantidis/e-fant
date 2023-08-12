import React, {useEffect, useState} from "react";

function RestaurantsInfo(props) {

    let restaurantId = props.restaurant;

    let [restaurant, setRestaurant] = useState([]);


    let authCredentials = localStorage.getItem("authToken");



    useEffect(() => {
        fetch(`http://localhost:8080/restaurants/${restaurantId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }

        })
            .then(response => response.json())
            .then(data => {
                setRestaurant(data);
                console.log("Data type:", typeof data); // Check the data type
                console.log("Data:", data); // Check the data content

            });
    }, []);


    return (
        <div className="tm-site-header">
            <h3 className="tm-site-name">{restaurant.name}</h3>

        </div>
    );
}

export default RestaurantsInfo;