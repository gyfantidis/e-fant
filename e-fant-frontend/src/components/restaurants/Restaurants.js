import Restaurant from "./Restaurant";
import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

function Restaurants(props) {

    let category = useParams();



    let [restaurants, setRestaurants] = useState([]);


   // let authCredentials = localStorage.getItem("authToken");
    const basicAuth = btoa(`user1@example.com:hashed_password_1`);



    useEffect(() => {
        let apiUrl = `http://52.206.235.17:8080/restaurants`;

        if ([2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14].includes(Number(category.id))) {
            apiUrl = `http://52.206.235.17:8080/restaurants/category/${category.id}`;
        }

        fetch(apiUrl, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + basicAuth
            }
        })
            .then(response => response.json())
            .then(data => {
                setRestaurants(data);
                console.log("Data type:", typeof data);
                console.log("Data:", data);
            });
    }, [category.id, basicAuth]);


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