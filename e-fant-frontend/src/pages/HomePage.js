import '../assets/fontawesome/css/all.min.css'; // Import Font Awesome CSS
import '../assets/css/tooplate-wave-cafe.css'; // Import Custom CSS

import Header from "../components/Header";
import React, {useEffect, useState} from "react";
import Restaurants from "../components/restaurants/Restaurants";
import {useParams} from "react-router-dom";


function HomePage(props) {

    let category = useParams();

    let [selectedCategory, setSelectedCategory] = useState({})

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




    return (


        <div className="tm-row">
            <Header selectedCategory = {selectedCategory} user={user} setLogoutTriggered = {setLogoutTriggered} />


            <div className="tm-right">
                <main className="tm-main">

                    <Restaurants category={{category}} selectedCategory = {selectedCategory} />

                </main>

            </div>
        </div>




)
    ;
}

export default HomePage;
