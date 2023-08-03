import '../assets/fontawesome/css/all.min.css'; // Import Font Awesome CSS
import '../assets/css/tooplate-wave-cafe.css'; // Import Custom CSS

import Header from "../components/Header";
import React, {useEffect, useState} from "react";
import Restaurants from "../components/restaurants/Restaurants";




function HomePage(props) {

    let [authToken, setAuthToken] = useState("");

    useEffect(() => {
        setAuthToken(localStorage.getItem("authToken"));
    })



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
            setLogoutTriggered(false);
        }
    }, [loginTriggered, logoutTriggered])

    return (


        <div className="tm-row">
            {/*<Background/>*/}
            {/*<div className="tm-video-wrapper">*/}
            {/*    <video autoPlay muted loop id="tm-video">*/}
            {/*        <source src={require("./assets/video/wave-cafe-video-bg.mp4")} type="video/mp4" />*/}
            {/*    </video>*/}
            {/*</div>*/}


            <Header selectedCategory = {selectedCategory} user={user} setLogoutTriggered = {setLogoutTriggered}>



            </Header>


            <div className="tm-right">
                <main className="tm-main">


                    <Restaurants selectedCategory = {selectedCategory}>

                    </Restaurants>

                    {/*<RestaurantMenu>*/}

                    {/*</RestaurantMenu>*/}

                </main>

            </div>


        </div>




)
    ;
}

export default HomePage;
