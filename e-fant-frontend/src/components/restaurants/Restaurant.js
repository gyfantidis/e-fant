import React from 'react';
import RestaurantReview from "./RestaurantReview";
import {Link, useParams} from "react-router-dom";

function Restaurant(props) {
    let restaurant = props.restaurant;



    return (

        <div className="tm-black-bg tm-special-item">

            <img src={`/img/restaurants/${restaurant.imageUrl}`}/>

            <div className="tm-special-item-description">
                <h2 className="tm-text-primary tm-special-item-title">
                    {localStorage.getItem("authToken") ? (
                        <Link to={`/restaurant/${restaurant.restaurantId}/item/${0}`}>
                            {restaurant.name}
                        </Link>
                    ) : (
                        <Link onClick={() => alert("To continue, please login or sign up.")}>
                            {restaurant.name}
                        </Link>
                    )}
                </h2>
                <h2 className="tm-text-primary tm-special-item-title"></h2>
                <p className="tm-special-item-text">{restaurant.description}</p>
                <RestaurantReview />
            </div>


        </div>

    );
}

export default Restaurant;

