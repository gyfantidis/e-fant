import '../assets/fontawesome/css/all.min.css'; // Import Font Awesome CSS
import '../assets/css/tooplate-wave-cafe.css'; // Import Custom CSS

import Header from "../components/Header";
import React, {useState} from "react";
import Restaurants from "../components/restaurants/Restaurants";

import RestaurantMenu from "../components/restaurants/RestaurantMenu";



function RestaurantPage(props) {

    let [selectedCategory, setSelectedCategory] = useState({})

    return (

        <div className="tm-row">
            {/*<Background/>*/}
            {/*<div className="tm-video-wrapper">*/}
            {/*    <video autoPlay muted loop id="tm-video">*/}
            {/*        <source src={require("./assets/video/wave-cafe-video-bg.mp4")} type="video/mp4" />*/}
            {/*    </video>*/}
            {/*</div>*/}
            <Header selectedCategory = {selectedCategory} >

            </Header>
            <div className="tm-right">
                <main className="tm-main">

                    <RestaurantMenu>

                    </RestaurantMenu>

                </main>

            </div>


        </div>




    )
        ;
}

export default RestaurantPage;
