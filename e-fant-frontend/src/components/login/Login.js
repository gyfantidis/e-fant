import React from "react";
import {Link} from "react-router-dom";


function Login(props) {


    return (


            <div className="tm-site-footer">
                <p className="tm-black-bg tm-footer-text">
                    <a className="tm-footer-link" rel="sponsored"><Link to={'/login'}>Log in </Link>  </a>    /
                    <a className="tm-footer-link" rel="sponsored"><Link to={'/signup'}> Sign up</Link> </a>
                </p>
            </div>


    )
}

export default Login;