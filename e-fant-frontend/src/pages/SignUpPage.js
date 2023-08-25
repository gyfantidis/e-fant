import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";


function SignUpPage(props) {
    const now = new Date(); // Get current date and time


    let [user, setUser] = useState({
        username: "",
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        password: "",
        createdAt: now

    });

    const navigate = useNavigate();

    const [showPassword, setShowPassword] = useState(false); // State for password visibility


    const [registrationStatus, setRegistrationStatus] = useState("initial"); // "initial", "success", "failure"


    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setUser((prevUser) => ({
            ...prevUser,
            [name]: value,
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const basicAuth = btoa(`user1@example.com:hashed_password_1`);

        try {
            // Send the updated user profile to the server
            const response = await fetch(`http://3.93.11.35:8080/users/signup`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Basic " + basicAuth
                },
                body: JSON.stringify(user), // Send the user object as JSON
            })


            if (response.ok) {
                // Successfully registered the user
                console.log("User registered successfully.");
                alert("User registered successfully.");
                setRegistrationStatus("success"); // Set the registration status to success
                navigate('/login');
            } else {
                // Handle error response
                console.error("User registration failed.");
                alert("User registration failed.");
                setRegistrationStatus("failure"); // Set the registration status to failure

            }
        } catch (error) {
            console.error("An error occurred:", error);
            alert("An error occurred:", error);
            setRegistrationStatus("failure"); // Set the registration status to failure

        }
    };





    return (
        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Sign Up</h2>
                <p>Sign up now to explore a world of delicious flavors! Join us and unlock the ability to order your
                    favorite food with ease. Indulge in a culinary adventure right from the comfort of your home or
                    wherever you are.</p>

                <button className="tm-page-nav-item tm-btn-primary">

                    <Link to={`/`}>
                        Back to Menu
                    </Link>
                </button>
            </div>

            <div className="tm-black-bg tm-contact-form-container tm-align-left">
                <form onSubmit={handleSubmit} id="contact-form">
                    <div className="tm-form-group">
                        <label htmlFor="username">Username:</label>
                        <input
                            type="text"
                            name="username"
                            className="tm-form-control"
                            value={user.username}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label htmlFor="firstName">First Name:</label>
                        <input
                            type="text"
                            name="firstName"
                            className="tm-form-control"
                            value={user.firstName}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label htmlFor="lastName">Last Name:</label>
                        <input
                            type="text"
                            name="lastName"
                            className="tm-form-control"
                            value={user.lastName}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label htmlFor="email">Email:</label>
                        <input
                            type="email"
                            name="email"
                            className="tm-form-control"
                            value={user.email}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label htmlFor="phone">Phone:</label>
                        <input
                            type="tel"
                            name="phone"
                            className="tm-form-control"
                            value={user.phone}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label htmlFor="password">Password:</label>
                        <input
                            type={showPassword ? "text" : "password"} // Toggle password visibility
                            name="password"
                            className="tm-form-control"
                            value={user.password}
                            onChange={handleInputChange}
                        />
                        <label>
                            <input
                                type="checkbox"
                                onChange={() => setShowPassword(!showPassword)} // Toggle password visibility on checkbox change
                            />{" "}
                            Show Password
                        </label>
                    </div>
                    <div>
                        <button type="submit" className="tm-btn-primary tm-align-left">
                            Sign Up
                        </button>
                    </div>
                </form>
            </div>
        </div>


    );
}

export default SignUpPage;