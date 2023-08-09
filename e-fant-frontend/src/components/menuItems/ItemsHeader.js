import React from "react";
import {Link} from "react-router-dom";


function ItemHeader(){
    return(

        <nav className="tm-black-bg tm-drinks-nav">
            <ul>
                <li>
                    <h1 className="tm-tab-link" data-id="cold">Menu</h1>
                </li>
                <li>
                    <Link to={`/`}>
                        <h1  className="tm-tab-link" data-id="hot">Back</h1>
                    </Link>
                </li>



            </ul>
        </nav>



    );
}


export default ItemHeader;


