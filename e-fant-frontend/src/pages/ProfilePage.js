import React, {useEffect, useState} from "react";

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
                <h2 className="tm-text-primary">Users Profile</h2>
                <p>Welcome to your profile! Your culinary journey continues here. From here, you can manage your
                    profile.</p>


            </div>
            <div className="tm-black-bg tm-contact-text-container ">
                <h3 className="tm-text-primary">Username </h3><h2>{user.username}</h2>
                <h3 className="tm-text-primary">First Name </h3><h2>{user.firstName}</h2>
                <h3 className="tm-text-primary">Last Name </h3><h2>{user.lastName}</h2>
                <h3 className="tm-text-primary">e-mail </h3><h2>{user.email}</h2>
                <h3 className="tm-text-primary">Phone </h3><h2>{user.phone}</h2>


                <h3 className="tm-text-primary">Address</h3>
                {user.addresses && user.addresses.length > 0 ? (
                    <div>
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


                <p></p>
            </div>


        </div>


    );
}

export default ProfilePage;