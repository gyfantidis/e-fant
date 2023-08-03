import Restaurant from "./Restaurant";
import {useEffect, useState} from "react";

function Restaurants(props) {



    let [restaurants, setRestaurants] = useState([]);


    let authCredentials = localStorage.getItem("authToken");
    console.log(authCredentials)

    useEffect(() => {
        fetch(`http://localhost:8080/restaurants`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }

        })
            .then(response => response.json())
            .then(data => {
                setRestaurants(data);
                console.log("Data type:", typeof data); // Check the data type
                console.log("Data:", data); // Check the data content

            });
    }, []);


    return (
        <div className="follower-list-container">
            <div id="special" className="tm-page-content">
                <div className="tm-special-items">

                    {restaurants.map((restaurant) => {
                        return <Restaurant restaurant={restaurant} key={restaurant.restaurantId}/>;
                    })}
                </div>
            </div>

        </div>
    );
}

export default Restaurants;