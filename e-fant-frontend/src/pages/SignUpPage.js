import React from "react";

function SignUpPage(props) {





    return (
        <div id="contact" className="tm-page-content">
            <div className="tm-black-bg tm-contact-text-container ">
                <h2 className="tm-text-primary">Sign Up</h2>
                <p>Sign up now to explore a world of delicious flavors! Join us and unlock the ability to order your favorite food with ease. Indulge in a culinary adventure right from the comfort of your home or wherever you are.</p>


            </div>

            <div className="tm-black-bg tm-contact-form-container tm-align-left">
                <form action="" method="POST" id="contact-form">
                    <div className="tm-form-group">
                        <input type="first_name" name="first_name" className="tm-form-control" placeholder="First Name" required=""/>
                    </div>
                    <div className="tm-form-group">
                        <input type="last_name" name="last_name" className="tm-form-control" placeholder="Last Name" required=""/>
                    </div>
                    <div className="tm-form-group">
                        <input type="username" name="username" className="tm-form-control" placeholder="Username" required=""/>
                    </div>
                    <div className="tm-form-group">
                        <input type="phone" name="phone" className="tm-form-control" placeholder="Phone" required=""/>
                    </div>
                    <div className="tm-form-group">
                        <input type="email" name="email" className="tm-form-control" placeholder="Email" required=""/>
                    </div>
                    <div className="tm-form-group">
                        <input type="password" name="password" className="tm-form-control" placeholder="Password" required=""/>
                    </div>
                    <div>
                        <button type="submit" className="tm-btn-primary tm-align-right">
                            Sign Up
                        </button>
                    </div>
                </form>
            </div>
        </div>


    );
}

export default SignUpPage;