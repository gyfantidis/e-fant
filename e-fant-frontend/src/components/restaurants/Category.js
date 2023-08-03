import React from "react";


function Category(props){
    let category = props.category;

    return(
        <li className="tm-page-nav-item">
            <a href="#drink" className="tm-page-link">
                <i className={`${category.icon} tm-page-link-icon`}></i>
                <span>{category.categoryName}</span>
            </a>
        </li>
    );
}

export default Category;