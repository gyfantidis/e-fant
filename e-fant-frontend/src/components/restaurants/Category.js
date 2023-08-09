import React from "react";
import {Link} from "react-router-dom";


function Category(props){
    let category = props.category;

    return(
        <li className="tm-page-nav-item">

                <Link to={`/${category.categoryId}`}>
                    <a  className="tm-page-link">
                    <i className={`${category.icon} tm-page-link-icon`}></i>
                    <span>{category.categoryName}</span>
                    </a>
                </Link>



        </li>
    );
}

export default Category;