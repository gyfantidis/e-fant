import React from "react";
import {Link} from "react-router-dom";

function OrderButton(props) {


    return (
        <div>

            <h3 className="tm-text-primary tm-list-item-name"><i className="fas fa-shopping-cart fa-1x tm-site-logo"></i><span
                className="tm-list-item-price">


                <Link to={`/FinalOrder`}>
                   <a  className="tm-tab-link active" data-id="cold">
                    <span>Make the Order</span>
                    </a>
                </Link>


            </span></h3>



            <br/>
        </div>
    );
}

export default OrderButton;