import React from "react";


function ItemHeader(){
    return(

        <nav className="tm-black-bg tm-drinks-nav">
            <ul>
                <li>
                    <a href="#" className="tm-tab-link active" data-id="cold">Iced Coffee</a>
                </li>
                <li>
                    <a href="#" className="tm-tab-link" data-id="hot">Hot Coffee</a>
                </li>
                <li>
                    <a href="#" className="tm-tab-link" data-id="juice">Fruit Juice</a>
                </li>
            </ul>
        </nav>



    );
}


export default ItemHeader;


