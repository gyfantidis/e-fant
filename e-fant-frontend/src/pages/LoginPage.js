import React, {useState} from "react";
import {useNavigate} from "react-router-dom";

function LoginPage(props) {

    let [loginForm, setLoginForm] = useState({email: "", password: ""});
    const navigate = useNavigate();

    const handleLoginSubmit = (event) => { 
        event.preventDefault();

        // Encode the username and password in the Base64 auth format
        const basicAuth = btoa(`${loginForm.email}:${loginForm.password}`);

        fetch(`http://3.93.11.35:8080/users?email=${loginForm.email}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + basicAuth
            }
        })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                alert("Invalid credentials");

            })
            .then(data => {
                localStorage.setItem("authToken", basicAuth);
                localStorage.setItem("loggedInUser", JSON.stringify(data[0]));



                navigate('/');

            });

    }

    const handleLoginChange = (event) => {
        setLoginForm({...loginForm, [event.target.name]: event.target.value});
    }





    return (
        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Log in</h2>
                <p>Login and deliver your favorite food with just a few taps! Satisfy your cravings and embark on a culinary journey from the comfort of your home or on-the-go.</p>
            </div>
            <div className="tm-black-bg tm-contact-form-container tm-align-left">
                <form onSubmit={handleLoginSubmit} id="contact-form">
                    <div className="tm-form-group">
                        <input type="email" name="email" className="tm-form-control" placeholder="Email" value={loginForm.email} onChange={handleLoginChange}/>
                    </div>
                    <div className="tm-form-group">
                        <input type="password" name="password" className="tm-form-control" placeholder="Password" value={loginForm.password} onChange={handleLoginChange}/>
                    </div>
                    <div>
                        <button type="submit" className="tm-btn-primary tm-align-right">
                            Login
                        </button>
                    </div>
                </form>
            </div>
        </div>


    );
}

export default LoginPage;