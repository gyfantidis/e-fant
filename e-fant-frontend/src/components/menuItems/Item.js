import React from "react";
import {Link} from "react-router-dom";

function Item(props){

    let item = props.item;


    return(
        <div className="tm-list-item">
            <img src={`/img/items/${item.imageUrl}`} alt="Image" className="tm-list-item-img"/>
            <div className="tm-black-bg tm-list-item-text">
                <h3 className="tm-list-item-name">{item.name}<span
                    className="tm-list-item-price">{item.price}</span></h3>
                <p className="tm-list-item-description">{item.description}</p>
                <h3 className="tm-text-primary tm-list-item-name"><i className="fas fa-cart-plus fa-1x tm-site-logo"></i><span
                    className="tm-list-item-price">
                    <Link to={`/restaurant/${props.restaurant}/item/${item.itemId}`}>
                        Add to Cart
                    </Link>
                </span>
                </h3>


                    {/*<a href="#" className="tm-tab-link active" data-id="cold">Add to Cart</a></span></h3>*/}
            </div>
        </div>
    );
}

export default Item;