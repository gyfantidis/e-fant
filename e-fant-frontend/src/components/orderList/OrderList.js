import React, {useEffect, useState} from "react";
import RestaurantsInfo from "./RestaurantsInfo";
import OrderItems from "./OrderItems";


function OrderList(props) {


    return (
        <div className="tm-black-bg tm-list-item-text">

            <RestaurantsInfo restaurant={props.restaurant}/>

            <OrderItems item={props.item} restaurant={props.restaurant}/>

        </div>
    );
}

export default OrderList;