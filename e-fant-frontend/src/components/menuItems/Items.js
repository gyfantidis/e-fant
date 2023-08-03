import {useEffect, useState} from "react";
import Item from "./Item";
import {useSearchParams} from "react-router-dom";


function Items(props) {


            // image: require('../../assets/img/smoothie-2.png'),


    let [items, setItems] = useState([]);


    let authCredentials = localStorage.getItem("authToken");
    console.log(authCredentials)


    useEffect(() => {
        fetch(`http://localhost:8080/restaurants/items/22`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Basic " + authCredentials
            }

        })
            .then(response => response.json())
            .then(data => {
                setItems(data);
                console.log("Data type:", typeof data); // Check the data type
                console.log("Data:", data); // Check the data content

            });
    }, []);

    return (
        <div className="tm-list">

            {items.map((item) => {
                return <Item item={item} key={item.itemId}/>;
            })}
        </div>


    );
}


export default Items;