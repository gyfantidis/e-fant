import {useEffect, useState} from "react";
import Category from "./Category";



function Categories(props) {


    let [categories, setCategories] = useState([])

   // let authCredentials = localStorage.getItem("authToken");
    const basicAuth = btoa(`user1@example.com:hashed_password_1`);



    useEffect(() => {
        fetch(`http://3.93.11.35:8080/restaurants/categories`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + basicAuth
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