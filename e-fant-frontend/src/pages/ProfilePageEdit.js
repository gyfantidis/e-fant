import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";

function ProfilePageEdit(props) {

    let [user, setUser] = useState({});


    let [authToken, setAuthToken] = useState("");

    useEffect(() => {
        setAuthToken(localStorage.getItem("authToken"));
    })


    const [showPassword, setShowPassword] = useState(false); // State for password visibility

    const navigate = useNavigate();

    const now = new Date(); // Get current date and time


    useEffect(() => {
        let loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
        if (loggedInUser) {
            setUser(loggedInUser);
        }
    }, [])





    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setUser((prevUser) => ({
            ...prevUser,
            [name]: value,
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const basicAuth = authToken;



        try {
            // Send the updated user profile to the server
            const response = await fetch(`http://localhost:8080/users/${user.id}`, {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Basic " + basicAuth
                },
                body: JSON.stringify(user), // Send the user object as JSON
            });




            if (response.ok) {
                // Successfully updated the profile
                console.log('Profile updated successfully.');
                alert('Profile updated successfully.');
                localStorage.removeItem("loggedInUser");
                localStorage.setItem("loggedInUser", JSON.stringify(user));
                navigate('/profile');
            } else {
                // Handle error response
                console.error('Profile update failed.');
                alert('Profile update failed.');
            }
        } catch (error) {
            console.error('An error occurred:', error);
            alert('An error occurred:', error);
        }
    };


    return (

        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Edit Profile</h2>
                <p>Welcome to your profile! Your culinary journey continues here. From here, you can manage your
                    profile.</p>

                <button className="tm-page-nav-item tm-btn-primary">

                    <Link to={`/`}>
                        Back to Menu
                    </Link>
                </button>


            </div>
            <div className="tm-black-bg tm-contact-text-container ">

                <form onSubmit={handleSubmit} id="contact-form">
                    <div className="tm-form-group">
                        <label htmlFor="username">Username:</label>
                        <input
                            type="text"
                            name="username"
                            className="tm-form-control"
                            placeholder="Username"
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
                            placeholder="First Name"
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
                            placeholder="Last Name"
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
                            placeholder="Email"
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
                            placeholder="Phone"
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
                            placeholder="Password"
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
                            Save Changes
                        </button>
                    </div>
                </form>


                {/*<h2 className="tm-text-primary">Username : {user.username}</h2>*/}
                {/*<h2 className="tm-text-primary">First Name : {user.firstName}</h2>*/}
                {/*<h2 className="tm-text-primary">Last Name : {user.lastName}</h2>*/}
                {/*<h2 className="tm-text-primary">e-mail : {user.email}</h2>*/}
                {/*<h2 className="tm-text-primary">Phone : {user.phone}</h2>*/}


                {/*<h2>Address</h2>*/}
                {/*{user.addresses && user.addresses.length > 0 ? (*/}
                {/*    <div className="tm-text-primary">*/}
                {/*        <p>*/}
                {/*            {user.addresses[0].address} - {user.addresses[0].addressNumber} - {user.addresses[0].floor}*/}
                {/*        </p>*/}
                {/*        <p>*/}
                {/*            {user.addresses[0].city} - Zip code: {user.addresses[0].zipCode}*/}
                {/*        </p>*/}

                {/*    </div>*/}
                {/*) : (*/}
                {/*    <p>No address available.</p>*/}
                {/*)}*/}


            </div>


        </div>


    );
}

export default ProfilePageEdit;