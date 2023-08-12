import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";

function ProfilePage(props) {

    let [user, setUser] = useState({});


    useEffect(() => {
        let loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
        if (loggedInUser) {
            setUser(loggedInUser);
        }
    }, [])


    return (

        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Your Profile</h2>
                <p>Welcome to your profile! Your culinary journey continues here. From here, you can manage your
                    profile.</p>
                <button className="tm-page-nav-item tm-btn-primary">

                    <Link to={`/`}>
                        Back to Menu
                    </Link>
                </button>


            </div>
            <div className="tm-black-bg tm-contact-text-container ">


                <h2 className="tm-text-primary">Username : {user.username}</h2>
                <h2 className="tm-text-primary">First Name : {user.firstName}</h2>
                <h2 className="tm-text-primary">Last Name : {user.lastName}</h2>
                <h2 className="tm-text-primary">e-mail : {user.email}</h2>
                <h2 className="tm-text-primary">Phone : {user.phone}</h2>


                <h2>Address</h2>
                {user.addresses && user.addresses.length > 0 ? (
                    <div className="tm-text-primary">
                        <p>
                            {user.addresses[0].address} - {user.addresses[0].addressNumber} - {user.addresses[0].floor}
                        </p>
                        <p>
                            {user.addresses[0].city} - Zip code: {user.addresses[0].zipCode}
                        </p>

                    </div>
                ) : (
                    <p>No address available.</p>
                )}
                <button className="tm-page-nav-item tm-btn-primary">

                    <Link to={`/profile/edit`}>
                        Edit Profile
                    </Link>
                </button>



                <br/>


            </div>


        </div>


    );
}

export default ProfilePage;