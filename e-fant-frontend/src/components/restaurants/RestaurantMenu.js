import React from "react";
import ItemHeader from "../menuItems/ItemsHeader";
import Items from "../menuItems/Items";


function RestaurantMenu(props) {


    return (

        <div id="drink" className="tm-page-content">

            <ItemHeader/>


            <Items restaurant={props.restaurant}/>




        </div>

    );
}

export default RestaurantMenu;