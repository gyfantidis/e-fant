import {useEffect, useState} from "react";
import Category from "./Category";



function Categories() {


    let [categories, setCategories] = useState([])

    let authCredentials = localStorage.getItem("authToken");
    console.log(authCredentials)

    useEffect(() => {
        fetch(`http://localhost:8080/restaurants/categories`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }

        })
            .then(response => response.json())
            .then(data => {
                setCategories(data);
                console.log(data);
            });
    }, [])

    return (
        <nav className="tm-site-nav">
            <ul className="tm-site-nav-ul">
                {categories.map((category) => {
                        return <Category category={category} key={category.categoryId}/>;
                    }
                )}
            </ul>
        </nav>
    );


}

export default Categories;