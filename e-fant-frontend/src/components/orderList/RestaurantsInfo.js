import React from "react";

function RestaurantsInfo(props) {


    return (
        <div>
            <h3 className="tm-text-primary tm-special-item-title">{props.restaurant.name}</h3>
            <h3 className="tm-list-item-name">{props.restaurant.description}</h3>
            <h3 className="tm-list-item-name">{props.restaurant.address}</h3>
            <h3 className="tm-list-item-name">{props.restaurant.phone}</h3>
            <br/>
        </div>
    );
}

export default RestaurantsInfo;