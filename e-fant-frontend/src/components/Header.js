import React from 'react';
import Categories from "./restaurants/Categories";
import Login from "./login/Login";
import User from "./login/User";
import EfantLabel from "./EfantLabel";
import Footer from "./Footer";


function Header(props) {


    //  let profileURL = `/users?userId=${props.user.id}`;

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
                <Categories/>
                <Footer/>
            </div>
        </header>
    );
}

export default Header;