import React from "react";
import {Link, useNavigate} from "react-router-dom";
import Logout from "./Logout";


function User(props) {




    return (


        <div className="tm-site-footer">
            <p className="tm-black-bg tm-footer-text">
                <a className="tm-footer-link" rel="sponsored">Welcome <Link to={'/profile'}>{props.user.firstName} {props.user.lastName} </Link>  </a>
            </p>
            <Logout user={props.user} setLogoutTriggered = {props.setLogoutTriggered}/>
        </div>


    )
}

export default User;