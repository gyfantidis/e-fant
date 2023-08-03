import React from "react";
import {Link, useNavigate} from "react-router-dom";


function Logout(props) {

    const navigate = useNavigate();

    let logout = () => {
        props.setLogoutTriggered(true);
        navigate('/');
    }


    return (
            <p className="tm-black-bg tm-footer-text">
                <a className="tm-footer-link" rel="sponsored"> <Link onClick={logout}> Logout </Link>  </a>


            </p>


    )
}

export default Logout;