import React from "react";
import ItemHeader from "../menuItems/ItemsHeader";
import Items from "../menuItems/Items";
import RestaurantsInfo from "../orderList/RestaurantsInfo";


function RestaurantMenu(props) {


    return (

        <div id="drink" className="tm-page-content">

            <RestaurantsInfo restaurant={props.restaurant}/>

            <ItemHeader />


            <Items restaurant={props.restaurant}/>




        </div>

    );
}

export default RestaurantMenu;