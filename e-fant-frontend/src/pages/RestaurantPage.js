import '../assets/fontawesome/css/all.min.css'; // Import Font Awesome CSS
import '../assets/css/tooplate-wave-cafe.css'; // Import Custom CSS


import React, {useEffect, useState} from "react";
import RestaurantMenu from "../components/restaurants/RestaurantMenu";
import {useParams} from "react-router-dom";
import RestaurantHeader from "../components/RestaurantHeader";


function RestaurantPage(props) {



    const {restaurantId, itemId} = useParams();



    let [user, setUser] = useState({});
    let [loginTriggered, setLoginTriggered] = useState(false);
    let [logoutTriggered, setLogoutTriggered] = useState(false);


    useEffect(() => {
        let loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
        if (loggedInUser) {
            setUser(loggedInUser);
        }
    }, [])

    useEffect(() => {
        if (loginTriggered) {
            let loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
            if (loggedInUser) {
                setUser(loggedInUser);
            }
            setLoginTriggered(false);
        }
        if (logoutTriggered) {
            setUser({});
            localStorage.removeItem("loggedInUser");
            localStorage.removeItem("authToken");
            // Check if "restaurant" exists in local storage before removing
            if (localStorage.getItem("restaurant")) {
                localStorage.removeItem("restaurant");
            }

            // Check if "items" exists in local storage before removing
            if (localStorage.getItem("items")) {
                localStorage.removeItem("items");
            }
            setLogoutTriggered(false);
        }
    }, [loginTriggered, logoutTriggered])

    if (!restaurantId) {
        return <div>Loading...</div>; // or handle the loading state appropriately
    }


    return (


        <div className="tm-row">

            <RestaurantHeader item={{itemId}} restaurant={restaurantId} user={user}
                              setLogoutTriggered={setLogoutTriggered}/>


            <div className="tm-right">
                <main className="tm-main">

                    <RestaurantMenu restaurant={restaurantId}>

                    </RestaurantMenu>

                </main>
            </div>


        </div>


    )
        ;
}

export default RestaurantPage;
