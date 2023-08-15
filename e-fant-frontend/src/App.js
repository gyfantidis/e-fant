import './App.css';
import './assets/fontawesome/css/all.min.css'; // Import Font Awesome CSS
import './assets/css/tooplate-wave-cafe.css'; // Import Custom CSS


import React, {useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import RestaurantPage from "./pages/RestaurantPage";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/SignUpPage";
import ProfilePage from "./pages/ProfilePage";
import ProfilePageEdit from "./pages/ProfilePageEdit";
import FinalOrder from "./pages/FinalOrder";
import CreateAddress from "./pages/CreateAddress";



function App() {


  //  let [loginTriggered, setLoginTriggered] = useState(false);


    return (

        <BrowserRouter>
            <div className="tm-container">

                <Routes>

                    <Route index element={<HomePage/>}/>
                    <Route
                        path="/restaurant/:restaurantId/item/:itemId"
                        element={<RestaurantPage/>}
                    />
                    <Route path="/:id" element={<HomePage/>}/>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/login" element={<LoginPage /*setLoginTriggered={setLoginTriggered}*/ />}/>
                    <Route path="/signup" element={<SignUpPage/>}/>
                    <Route path="/profile" element={<ProfilePage/>}/>
                    <Route path="/profile/edit" element={<ProfilePageEdit/>}/>
                    <Route path="/final-order" element={<FinalOrder/>}/>
                    <Route path="/create-address" element={<CreateAddress/>}/>



                </Routes>


            </div>


        </BrowserRouter>


    );
}

export default App;
