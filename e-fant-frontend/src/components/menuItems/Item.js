import React from "react";

function Item(props){

    let item = props.item;
    return(
        <div className="tm-list-item">
            <img src={item.imageUrl} alt="Image" className="tm-list-item-img"/>
            <div className="tm-black-bg tm-list-item-text">
                <h3 className="tm-list-item-name">{item.name}<span
                    className="tm-list-item-price">{item.price}</span></h3>
                <p className="tm-list-item-description">{item.description}</p>
                <a href="#" className="tm-tab-link active" data-id="cold">Add to Cart</a>

            </div>
        </div>
    );
}

export default Item;