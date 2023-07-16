-- Create the schema
CREATE SCHEMA IF NOT EXISTS efant;

-- Roles table
CREATE TABLE IF NOT EXISTS efant.roles (
                                           role_id BIGSERIAL PRIMARY KEY,
                                           role_name VARCHAR(50)
    );

-- Users table
CREATE TABLE IF NOT EXISTS efant.users (
                                           user_id BIGSERIAL PRIMARY KEY,
                                           username VARCHAR(50),
    password VARCHAR(100),
    email VARCHAR(100),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(20),
    role_id BIGINT,
    created_at TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES efant.roles(role_id)
    );

-- UserAddresses table
CREATE TABLE IF NOT EXISTS efant.addresses (
                                               address_id BIGSERIAL PRIMARY KEY,
                                               address VARCHAR(100),
    address_number VARCHAR(20),
    floor VARCHAR(20),
    ring_name VARCHAR(50),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    comments VARCHAR(200),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES efant.users(user_id)
    );

-- Restaurants table
CREATE TABLE IF NOT EXISTS efant.restaurants (
                                                 restaurant_id BIGSERIAL PRIMARY KEY,
                                                 name VARCHAR(100),
    description TEXT,
    address VARCHAR(100),
    phone VARCHAR(20),
    image_url VARCHAR(200),
    created_at TIMESTAMP
    );

-- RestaurantCategories table
CREATE TABLE IF NOT EXISTS efant.restaurant_categories (
                                                           category_id BIGSERIAL PRIMARY KEY,
                                                           category_name VARCHAR(50)
    );

-- Restaurant_Category_Mapping table
CREATE TABLE IF NOT EXISTS efant.restaurant_category_mapping (
                                                                 mapping_id BIGSERIAL PRIMARY KEY,
                                                                 restaurant_id BIGINT,
                                                                 category_id BIGINT,
                                                                 FOREIGN KEY (restaurant_id) REFERENCES efant.restaurants(restaurant_id),
    FOREIGN KEY (category_id) REFERENCES efant.restaurant_categories(category_id)
    );

-- MenuItems table
CREATE TABLE IF NOT EXISTS efant.menu_items (
                                                item_id BIGSERIAL PRIMARY KEY,
                                                restaurant_id BIGINT,
                                                name VARCHAR(100),
    description TEXT,
    price DECIMAL(8, 2),
    image_url VARCHAR(200),
    FOREIGN KEY (restaurant_id) REFERENCES efant.restaurants(restaurant_id)
    );



-- Orders table
CREATE TABLE IF NOT EXISTS efant.orders (
                                            order_id BIGSERIAL PRIMARY KEY,
                                            user_id BIGINT,
                                            restaurant_id BIGINT,
                                            order_date TIMESTAMP,
                                            total_amount DECIMAL(8, 2),
    delivery_address_id BIGINT,
    notes VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES efant.users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES efant.restaurants(restaurant_id),
    FOREIGN KEY (delivery_address_id) REFERENCES efant.addresses(address_id)
    );

-- OrderItems table
CREATE TABLE IF NOT EXISTS efant.order_items (
                                                 order_item BIGSERIAL PRIMARY KEY,
                                                 item_id BIGINT,
                                                 order_id BIGINT,
                                                 quantity INTEGER,
                                                 notes VARCHAR(200),
    FOREIGN KEY (item_id) REFERENCES efant.menu_items(item_id),
    FOREIGN KEY (order_id) REFERENCES efant.orders(order_id)
    );

-- OrderStatus table
CREATE TABLE IF NOT EXISTS efant.order_status (
                                                  order_status_id BIGSERIAL PRIMARY KEY,
                                                  order_id BIGINT,
                                                  received BOOLEAN,
                                                  processing BOOLEAN,
                                                  on_the_road BOOLEAN,
                                                  delivered BOOLEAN,
                                                  delivery_date TIMESTAMP,
                                                  status VARCHAR(20),
    FOREIGN KEY (order_id) REFERENCES efant.orders(order_id)
    );

-- Reviews table
CREATE TABLE IF NOT EXISTS efant.reviews (
                                             review_id BIGSERIAL PRIMARY KEY,
                                             user_id BIGINT,
                                             restaurant_id BIGINT,
                                             rating INTEGER,
                                             comment TEXT,
                                             review_date TIMESTAMP,
                                             FOREIGN KEY (user_id) REFERENCES efant.users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES efant.restaurants(restaurant_id)
    );

-- Favorites table
CREATE TABLE IF NOT EXISTS efant.favorites (
                                               favorite_id BIGSERIAL PRIMARY KEY,
                                               user_id BIGINT,
                                               restaurant_id BIGINT,
                                               FOREIGN KEY (user_id) REFERENCES efant.users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES efant.restaurants(restaurant_id)
    );


