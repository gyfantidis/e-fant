import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";


function CreateAddress(props) {

    const navigate = useNavigate();

    const user = JSON.parse(localStorage.getItem("loggedInUser")) || {};

    let [address, setAddress] = useState({
        address: "",
        addressNumber: "",
        floor: "",
        ringName: "",
        city: "",
        state: "",
        zipCode: "",
        comments: "",
        userId: null
    });

    useEffect(() => {
        if (user) {
            setAddress((prevAddress) => ({
                ...prevAddress,
                userId: user.id
            }));
        }
    }, [user]);


    let authCredentials = localStorage.getItem("authToken");


    const [registrationStatus, setRegistrationStatus] = useState("initial"); // "initial", "success", "failure"


    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setAddress((prevUser) => ({
            ...prevUser,
            [name]: value,
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();


        try {
            // Send the updated user profile to the server
            const response = await fetch(`http://52.206.235.17:8080/address`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Basic " + authCredentials
                },
                body: JSON.stringify(address), // Send the user object as JSON
            })


            if (response.ok) {
                // Successfully registered the user
                console.log("Address registered successfully.");
                alert("Address registered successfully.");
                setRegistrationStatus("success"); // Set the registration status to success
                findTheUserAgain();
                navigate('/profile');


            } else {
                // Handle error response
                console.error("Address registration failed.");
                alert("Address registration failed.");
                setRegistrationStatus("failure"); // Set the registration status to failure

            }
        } catch (error) {
            console.error("An error occurred:", error);
            alert("An error occurred:", error);
            setRegistrationStatus("failure"); // Set the registration status to failure

        }
    };


    const findTheUserAgain = () => {
        fetch(`http://52.206.235.17:8080/users/${address.userId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }
        })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
                alert("Invalid credentials");

            })
            .then(data => {
                localStorage.removeItem("loggedInUser");
                localStorage.setItem("loggedInUser", JSON.stringify(data));
            })

            .catch(error => {
                console.error("An error occurred while fetching user data:", error);
            });

    }


    return (
        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Create Address</h2>
                <p>Join us for flavor-packed delights! Sign up and add your address – a must for all users to enjoy
                    seamless ordering.</p>

                <button className="tm-page-nav-item tm-btn-primary">

                    <Link to={`/profile`}>
                        Back to Profile
                    </Link>
                </button>
            </div>

            <div className="tm-black-bg tm-contact-form-container tm-align-left">
                <form onSubmit={handleSubmit} id="contact-form">
                    <div className="tm-form-group">
                        <label>Address:</label>
                        <input
                            type="text"
                            name="address"
                            className="tm-form-control"
                            value={address.address}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>Number:</label>
                        <input
                            type="text"
                            name="addressNumber"
                            className="tm-form-control"
                            value={address.addressNumber}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>Floor:</label>
                        <input
                            type="text"
                            name="floor"
                            className="tm-form-control"
                            value={address.floor}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>Ring Name:</label>
                        <input
                            type="text"
                            name="ringName"
                            className="tm-form-control"
                            value={address.ringName}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>City:</label>
                        <input
                            type="text"
                            name="city"
                            className="tm-form-control"
                            value={address.city}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>State:</label>
                        <input
                            type="text"
                            name="state"
                            className="tm-form-control"
                            value={address.state}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>Zip Code:</label>
                        <input
                            type="text"
                            name="zipCode"
                            className="tm-form-control"
                            value={address.zipCode}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className="tm-form-group">
                        <label>Comments:</label>
                        <input
                            type="text"
                            name="comments"
                            className="tm-form-control"
                            value={address.comments}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div>
                        <button type="submit" className="tm-btn-primary tm-align-left">
                            Create Address
                        </button>
                    </div>
                </form>
            </div>
        </div>


    );
}

export default CreateAddress;