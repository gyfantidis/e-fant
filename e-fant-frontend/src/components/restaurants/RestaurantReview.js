import React from "react";


function RestaurantReview(){
    return(
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <a href="#" style={{ marginRight: '20px' }} className="tm-tab-link active" data-id="cold">Rating
                <i className="fas fa-star" style={{ marginLeft: '5px' }}></i>4,1</a>
            <a href="#" style={{ marginRight: '20px' }} className="tm-tab-link active" data-id="cold">Comments
                <i className="fas fa-comments" style={{ marginLeft: '5px' }}> </i>  5</a>
            <a href="#" className="tm-tab-link active" data-id="cold">Favorites
                <i className="fas fa-heart" style={{ marginLeft: '5px' }}></i>18</a>
        </div>
    );
}

export default RestaurantReview;