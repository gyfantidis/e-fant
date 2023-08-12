import React, {useEffect, useState} from "react";

function OrderItem(props) {

    const handleDelete = () => {
        props.onDelete(props.index);
    };



    return (
        <div>
            <h3 className="tm-list-item-name">{props.item.name}
                <span className="tm-list-item-price"><button className="btn tm-btn-submit " onClick={handleDelete}>Delete </button></span>
                <span className="tm-list-item-price">{props.item.price}   </span>
            </h3>
            <br/>
        </div>


    );
}

export default OrderItem;