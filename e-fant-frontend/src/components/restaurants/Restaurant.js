import React from 'react';
import RestaurantReview from "./RestaurantReview";
import {Link} from "react-router-dom";

function Restaurant(props) {
    let restaurant = props.restaurant;

    const imageUrl = '../../assets/img/' + restaurant.imageUrl;


    return (

        <div className="tm-black-bg tm-special-item">


            {/*<img src={require(`../../assets/img/${restaurant.imageUrl}`)} alt="Restaurant" />*/}
            {/*<img src={require('{imageUrl}')} alt="Restaurant" />*/}
            {/*<img src={require(imageUrl)} alt="Restaurant" />*/}
            {/*<img src={imageUrl} alt="Restaurant" />*/}
            {/*`${BASE_URL}/assets/img/${restaurant.imageUrl}`*/}

            <img src={`/img/${restaurant.imageUrl}`}/>

            {/*<img src={"../../assets/img/restaurant_1.jpg"} />*/}


            <div className="tm-special-item-description">
                <h2 className="tm-text-primary tm-special-item-title"> <Link to={'/restaurant'}>{restaurant.name}</Link></h2>
                <h2 className="tm-text-primary tm-special-item-title"></h2>
                <p className="tm-special-item-text">{restaurant.description}</p>
                <RestaurantReview/>
            </div>
        </div>

    );
}

export default Restaurant;

