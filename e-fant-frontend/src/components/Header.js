import React from 'react';
import Categories from "./restaurants/Categories";
import Login from "./login/Login";
import User from "./login/User";


function Header(props) {


      let profileURL = `/users?userId=${props.user.id}`;

    return (
        <header className="tm-left">
            <div className="tm-left-inner">

                {/*if user is logged in show profile else login page*/}

                {props.user.email ?
                    <User user={props.user} setLogoutTriggered = {props.setLogoutTriggered}/>
                    :
                    <Login />
                }
                <div className="tm-site-header">
                    <i className="fas fa-shopping-cart fa-3x tm-site-logo"></i>
                    <h1 className="tm-site-name">e-fant App</h1>
                </div>
                <Categories/>
            </div>
        </header>
    );
}

export default Header;