import React from "react";
import ItemHeader from "../menuItems/ItemsHeader";
import Items from "../menuItems/Items";


function RestaurantMenu(props) {

    return (

        <div id="drink" className="tm-page-content">
            {/* Drink Menu Page */}
            <ItemHeader/>



                <Items>

                </Items>



            {/* end Drink Menu Page */}
        </div>

    );
}

export default RestaurantMenu;