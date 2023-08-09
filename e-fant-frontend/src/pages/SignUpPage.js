import React, {useState} from "react";
import {useHistory} from "react-router-dom"; // Import useHistory from React Router
import axios from "axios"; // Import axios for making HTTP requests


function SignUpPage(props) {
    // const history = useHistory(); // Initialize history from React Router
    //
    //
    // // State for form fields
    // const [formData, setFormData] = useState({
    //     first_name: "",
    //     last_name: "",
    //     username: "",
    //     phone: "",
    //     email: "",
    //     password: ""
    // });
    //
    // // Handle form field changes
    // const handleInputChange = (event) => {
    //     const {name, value} = event.target;
    //     setFormData((prevData) => ({
    //         ...prevData,
    //         [name]: value
    //     }));
    // };
    //
    //
    // // Handle form submission
    // const handleSubmit = async (event) => {
    //     event.preventDefault();
    //     try {
    //         // Make the POST request
    //         const response = await axios.post("http://localhost:8080/users", formData);
    //         console.log("POST response:", response.data); // You can handle the response here
    //
    //         // Redirect to "/" page after successful registration
    //         history.push("/");
    //     } catch (error) {
    //         console.error("Error:", error);
    //     }
    // };


    return (
        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Sign Up</h2>
                <p>Sign up now to explore a world of delicious flavors! Join us and unlock the ability to order your
                    favorite food with ease. Indulge in a culinary adventure right from the comfort of your home or
                    wherever you are.</p>
            </div>

            <div className="tm-black-bg tm-contact-form-container tm-align-left">
                {/*<form action="" method="POST" id="contact-form">*/}
                {/*    <div className="tm-form-group">*/}
                {/*        <input*/}
                {/*            type="text"*/}
                {/*            name="first_name"*/}
                {/*            className="tm-form-control"*/}
                {/*            placeholder="First Name"*/}
                {/*            value={formData.first_name}*/}
                {/*            onChange={handleInputChange}*/}
                {/*        />*/}
                {/*    </div>*/}
                {/*    <div className="tm-form-group">*/}
                {/*        <input*/}
                {/*            type="text"*/}
                {/*            name="last_name"*/}
                {/*            className="tm-form-control"*/}
                {/*            placeholder="Last Name"*/}
                {/*            value={formData.last_name}*/}
                {/*            onChange={handleInputChange}*/}
                {/*        />*/}
                {/*    </div>*/}
                {/*    <div className="tm-form-group">*/}
                {/*        <input*/}
                {/*            type="text"*/}
                {/*            name="username"*/}
                {/*            className="tm-form-control"*/}
                {/*            placeholder="Username"*/}
                {/*            value={formData.username}*/}
                {/*            onChange={handleInputChange}*/}
                {/*            required*/}
                {/*        />*/}
                {/*    </div>*/}
                {/*    <div className="tm-form-group">*/}
                {/*        <input*/}
                {/*            type="text"*/}
                {/*            name="phone"*/}
                {/*            className="tm-form-control"*/}
                {/*            placeholder="Phone"*/}
                {/*            value={formData.phone}*/}
                {/*            onChange={handleInputChange}*/}
                {/*            required*/}
                {/*        />*/}
                {/*    </div>*/}
                {/*    <div className="tm-form-group">*/}
                {/*        <input*/}
                {/*            type="email"*/}
                {/*            name="email"*/}
                {/*            className="tm-form-control"*/}
                {/*            placeholder="Email"*/}
                {/*            value={formData.email}*/}
                {/*            onChange={handleInputChange}*/}
                {/*            required*/}
                {/*        />*/}
                {/*    </div>*/}
                {/*    <div className="tm-form-group">*/}
                {/*        <input*/}
                {/*            type="password"*/}
                {/*            name="password"*/}
                {/*            className="tm-form-control"*/}
                {/*            placeholder="Password"*/}
                {/*            value={formData.password}*/}
                {/*            onChange={handleInputChange}*/}
                {/*            required*/}
                {/*        />*/}
                {/*    </div>*/}

                {/*    <div>*/}
                {/*        <button type="submit" className="tm-btn-primary tm-align-right">*/}
                {/*            Sign Up*/}
                {/*        </button>*/}
                {/*    </div>*/}
                {/*</form>*/}
            </div>
        </div>


    );
}

export default SignUpPage;