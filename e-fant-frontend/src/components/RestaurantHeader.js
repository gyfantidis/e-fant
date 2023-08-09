import React, {useEffect, useState} from 'react';
import Login from "./login/Login";
import User from "./login/User";
import EfantLabel from "./EfantLabel";
import OrderList from "./orderList/OrderList";
import Footer from "./Footer";



function RestaurantHeader(props) {


    //  let profileURL = `/users?userId=${props.user.id}`;

    let [restaurant, setRestaurant] = useState([]);
    let authCredentials = localStorage.getItem("authToken");

    useEffect(() => {
        let apiUrl = `http://localhost:8080/restaurants/${props.restaurant}`;

        fetch(apiUrl, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }
        })
            .then(response => response.json())
            .then(data => {
                setRestaurant(data);
                console.log("Data type:", typeof data);
                console.log("Data:", data);
            });
    }, []);





    return (
        <header className="tm-left">
            <div className="tm-left-inner">

                {/*if user is logged in show profile else login page*/}

                {props.user.email ?
                    <User user={props.user} setLogoutTriggered = {props.setLogoutTriggered}/>
                    :
                    <Login />
                }
                <EfantLabel/>
                <OrderList restaurant={restaurant} item={props.item}/>
                <Footer/>
            </div>
        </header>
    );
}

export default RestaurantHeader;