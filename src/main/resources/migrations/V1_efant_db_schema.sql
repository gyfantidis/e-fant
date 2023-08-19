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




ALTER TABLE efant.restaurant_categories ADD COLUMN IF NOT EXISTS icon VARCHAR(50);

INSERT INTO efant.restaurant_categories (category_name, icon)
VALUES
    ('All', 'fas fa-globe'),
    ('Pizza', 'fas fa-pizza-slice'),
    ('Burgers', 'fas fa-hamburger'),
    ('Greek Food', 'fas fa-globe-europe'),
    ('Asian Food', 'fas fa-utensils'),
    ('Coffees', 'fas fa-coffee'),
    ('Vegetarian Menu', 'fas fa-leaf'),
    ('Souvlaki', 'fas fa-utensils')
    ON CONFLICT DO NOTHING;

INSERT INTO efant.roles (role_name)
VALUES
    ('Admin'),
    ('Customer'),
    ('Restaurant Owner')
    ON CONFLICT DO NOTHING;

INSERT INTO efant.users (username, password, email, first_name, last_name, phone, role_id, created_at)
VALUES
    ('user1', 'hashed_password_1', 'user1@example.com', 'John', 'Doe', '+1234567890', 1, NOW()),
    ('user2', 'hashed_password_2', 'user2@example.com', 'Jane', 'Smith', '+9876543210', 2, NOW()),
    ('user3', 'hashed_password_3', 'user3@example.com', 'Michael', 'Johnson', '+1112223333', 3, NOW()),
    ('user4', 'hashed_password_4', 'user4@example.com', 'Emily', 'Anderson', '+4445556666', 2, NOW()),
    ('user5', 'hashed_password_5', 'user5@example.com', 'David', 'Lee', '+7778889999', 2, NOW()),
    ('user6', 'hashed_password_6', 'user6@example.com', 'Sarah', 'Wilson', '+1112223333', 2, NOW()),
    ('user7', 'hashed_password_7', 'user7@example.com', 'Alex', 'Davis', '+4445556666', 2, NOW()),
    ('user8', 'hashed_password_8', 'user8@example.com', 'Sophia', 'Moore', '+7778889999', 2, NOW()),
    ('user9', 'hashed_password_9', 'user9@example.com', 'William', 'Taylor', '+1112223333', 2, NOW()),
    ('user10', 'hashed_password_10', 'user10@example.com', 'Olivia', 'Brown', '+4445556666', 2, NOW()),
    ('user11', 'hashed_password_11', 'user11@example.com', 'James', 'Johnson', '+7778889999', 2, NOW()),
    ('user12', 'hashed_password_12', 'user12@example.com', 'Ava', 'Wilson', '+1112223333', 2, NOW()),
    ('user13', 'hashed_password_13', 'user13@example.com', 'Benjamin', 'Lee', '+4445556666', 2, NOW()),
    ('user14', 'hashed_password_14', 'user14@example.com', 'Mia', 'Smith', '+7778889999', 2, NOW()),
    ('user15', 'hashed_password_15', 'user15@example.com', 'Ethan', 'Taylor', '+1112223333', 2, NOW())
    ON CONFLICT DO NOTHING;

INSERT INTO efant.addresses (address, address_number, floor, ring_name, city, state, zip_code, comments, user_id)
VALUES
    ('123 Main Street', 'Apt 4', '2nd Floor', 'Ring B', 'New York', 'NY', '10001', 'Near the park', 1),
    ('456 Elm Avenue', 'Suite 10', '', 'Ring C', 'Los Angeles', 'CA', '90001', 'Office address', 2),
    ('789 Oak Drive', 'Unit 7', '3rd Floor', 'Ring A', 'Chicago', 'IL', '60601', 'Close to the station', 3),
    ('101 Maple Lane', 'Apt 2B', '1st Floor', 'Ring B', 'Houston', 'TX', '77001', 'Private entrance', 4),
    ('555 Pine Street', 'Apt 12', '', 'Ring C', 'Miami', 'FL', '33101', 'By the beach', 5),
    ('999 Cedar Avenue', 'Unit 5', '4th Floor', 'Ring A', 'Seattle', 'WA', '98101', 'City center', 6),
    ('333 Birch Road', 'Suite 20', '', 'Ring C', 'San Francisco', 'CA', '94101', 'Business district', 7),
    ('777 Walnut Court', 'Unit 8', '2nd Floor', 'Ring A', 'Boston', 'MA', '02101', 'Quiet neighborhood', 8),
    ('222 Ash Street', 'Apt 6B', '3rd Floor', 'Ring B', 'Denver', 'CO', '80201', 'Near the park', 9),
    ('444 Oakwood Drive', 'Suite 15', '', 'Ring C', 'Atlanta', 'GA', '30301', 'Great view', 10),
    ('666 Elmwood Avenue', 'Apt 9', '1st Floor', 'Ring B', 'Dallas', 'TX', '75201', 'Central location', 11),
    ('888 Maple Lane', 'Unit 18', '4th Floor', 'Ring A', 'Orlando', 'FL', '32801', 'Residential area', 12),
    ('111 Pine Street', 'Apt 22', '', 'Ring C', 'San Diego', 'CA', '92101', 'Near the beach', 13),
    ('333 Cedar Avenue', 'Suite 11', '2nd Floor', 'Ring A', 'Phoenix', 'AZ', '85001', 'Close to amenities', 14),
    ('555 Birch Road', 'Unit 3', '3rd Floor', 'Ring B', 'Washington, D.C.', 'DC', '20001', 'Historic district', 15)
    ON CONFLICT DO NOTHING;

INSERT INTO efant.restaurants (name, description, address, phone, image_url, created_at)
VALUES
    ('Pizza Place', 'Delicious pizza and Italian dishes', '123 Main Street', '+1122334455', 'restaurant_1.jpg', NOW()),
    ('Burger Joint', 'Juicy burgers and fries', '456 Elm Avenue', '+9988776655', 'restaurant_2.jpg', NOW()),
    ('Greek Taverna', 'Authentic Greek food and mezes', '789 Oak Drive', '+4455667788', 'restaurant_3.jpg', NOW()),
    ('Asian Delight', 'Flavorful Asian cuisine', '101 Maple Lane', '+1122334455', 'restaurant_4.jpg', NOW()),
    ('Coffee Haven', 'A variety of coffees and pastries', '555 Pine Street', '+9988776655', 'restaurant_5.jpg', NOW()),
    ('Green Bites', 'Vegetarian and vegan dishes', '999 Cedar Avenue', '+4455667788', 'restaurant_6.jpg', NOW()),
    ('Sizzling Souvlaki', 'Mouthwatering souvlaki wraps', '333 Birch Road', '+1122334455', 'restaurant_7.jpg', NOW()),
    ('Italian Delights', 'Classic Italian pasta and more', '777 Walnut Court', '+9988776655', 'restaurant_8.jpg', NOW()),
    ('Tasty Tacos', 'Tacos, burritos, and Mexican delights', '222 Ash Street', '+4455667788', 'restaurant_9.jpg', NOW()),
    ('Sushi Express', 'Fresh sushi and sashimi', '444 Oakwood Drive', '+1122334455', 'restaurant_10.jpg', NOW()),
    ('Pasta Paradise', 'Pasta dishes with various sauces', '666 Elmwood Avenue', '+9988776655', 'restaurant_11.jpg', NOW()),
    ('Healthy Bowls', 'Nourishing and colorful bowls', '888 Maple Lane', '+4455667788', 'restaurant_12.jpg', NOW()),
    ('Seafood Sensation', 'Fresh and flavorful seafood', '111 Pine Street', '+1122334455', 'restaurant_13.jpg', NOW()),
    ('Bistro Brunch', 'Brunch favorites and coffee', '333 Cedar Avenue', '+9988776655', 'restaurant_14.jpg', NOW()),
    ('BBQ Grill', 'Grilled meats and barbecue', '555 Birch Road', '+4455667788', 'restaurant_15.jpg', NOW()),
    ('Mediterranean Flavors', 'Mediterranean cuisine and mezes', '101 Maple Lane', '+1122334455', 'restaurant_16.jpg', NOW()),
    ('Steakhouse Grill', 'Juicy steaks and hearty meals', '123 Main Street', '+9988776655', 'restaurant_17.jpg', NOW()),
    ('Tandoori Express', 'Indian tandoori specialties', '456 Elm Avenue', '+4455667788', 'restaurant_18.jpg', NOW()),
    ('Thai Spice', 'Spicy and aromatic Thai dishes', '789 Oak Drive', '+1122334455', 'restaurant_19.jpg', NOW()),
    ('French Fusion', 'French dishes with a modern twist', '555 Pine Street', '+9988776655', 'restaurant_20.jpg', NOW()),
    ('Tapas Trail', 'Spanish tapas and sangria', '999 Cedar Avenue', '+4455667788', 'restaurant_21.jpg', NOW()),
    ('Middle Eastern Feast', 'Middle Eastern delights', '333 Birch Road', '+1122334455', 'restaurant_22.jpg', NOW()),
    ('Indian Spice', 'Authentic Indian curries', '777 Walnut Court', '+9988776655', 'restaurant_23.jpg', NOW()),
    ('Bakery Bliss', 'Freshly baked goods and pastries', '222 Ash Street', '+4455667788', 'restaurant_24.jpg', NOW()),
    ('Fusion Fare', 'Eclectic fusion cuisine', '444 Oakwood Drive', '+1122334455', 'restaurant_25.jpg', NOW()),
    ('Cajun Creations', 'Spicy Cajun dishes', '666 Elmwood Avenue', '+9988776655', 'restaurant_26.jpg', NOW()),
    ('Delightful Desserts', 'Delectable desserts and sweets', '888 Maple Lane', '+4455667788', 'restaurant_27.jpg', NOW()),
    ('Crispy Crusts', 'Crispy and cheesy pizzas', '111 Pine Street', '+1122334455', 'restaurant_28.jpg', NOW()),
    ('Comfort Classics', 'Homestyle comfort food', '333 Cedar Avenue', '+9988776655', 'restaurant_29.jpg', NOW()),
    ('Vietnamese Vibes', 'Vietnamese favorites', '555 Birch Road', '+4455667788', 'restaurant_30.jpg', NOW())


    ON CONFLICT DO NOTHING;



INSERT INTO efant.restaurant_category_mapping (restaurant_id, category_id)
VALUES
    (1, 1),   -- Pizza Place (restaurant_id: 13) is associated with category_id 7 (Vegetarian Menu)
    (2, 2),   -- Burger Joint (restaurant_id: 14) is associated with category_id 8 (Souvlaki)
    (3, 3),   -- Greek Taverna (restaurant_id: 15) is associated with category_id 2 (Pizza)
    (4,4),   -- Asian Delight (restaurant_id: 16) is associated with category_id 3 (Burgers)
    (5, 5),   -- Coffee Haven (restaurant_id: 17) is associated with category_id 5 (Asian Food)
    (6, 6),   -- Green Bites (restaurant_id: 18) is associated with category_id 6 (Coffees)
    (7, 7),   -- Sizzling Souvlaki (restaurant_id: 19) is associated with category_id 7 (Vegetarian Menu)
    (8, 1),   -- Italian Delights (restaurant_id: 20) is associated with category_id 2 (Pizza)
    (9, 2),   -- Tasty Tacos (restaurant_id: 21) is associated with category_id 3 (Burgers)
    (10, 4),   -- Sushi Express (restaurant_id: 22) is associated with category_id 5 (Asian Food)
    (11, 1),   -- Pasta Paradise (restaurant_id: 23) is associated with category_id 9 (Italian Food)
    (12, 6),   -- Healthy Bowls (restaurant_id: 24) is associated with category_id 7 (Vegetarian Menu)
    (13, 3),   -- Seafood Sensation (restaurant_id: 25) is associated with category_id 8 (Souvlaki)
    (14, 3),   -- Bistro Brunch (restaurant_id: 26) is associated with category_id 2 (Pizza)
    (15, 2),   -- BBQ Grill (restaurant_id: 27) is associated with category_id 3 (Burgers)
    (16, 3),   -- Mediterranean Flavors (restaurant_id: 28) is associated with category_id 4 (Greek Food)
    (17, 4),   -- Steakhouse Grill (restaurant_id: 29) is associated with category_id 5 (Asian Food)
    (18, 5),   -- Tandoori Express (restaurant_id: 30) is associated with category_id 6 (Coffees)
    (19, 6),   -- Thai Spice (restaurant_id: 31) is associated with category_id 7 (Vegetarian Menu)
    (20, 7),   -- French Fusion (restaurant_id: 32) is associated with category_id 8 (Souvlaki)
    (21, 2),   -- Tapas Trail (restaurant_id: 33) is associated with category_id 2 (Pizza)
    (22, 2),   -- Middle Eastern Feast (restaurant_id: 34) is associated with category_id 3 (Burgers)
    (23, 4),   -- Indian Spice (restaurant_id: 35) is associated with category_id 5 (Asian Food)
    (24, 6),   -- Bakery Bliss (restaurant_id: 36) is associated with category_id 7 (Vegetarian Menu)
    (25, 7),   -- Fusion Fare (restaurant_id: 37) is associated with category_id 8 (Souvlaki)
    (26, 1),   -- Cajun Creations (restaurant_id: 38) is associated with category_id 2 (Pizza)
    (27, 2),   -- Delightful Desserts (restaurant_id: 39) is associated with category_id 3 (Burgers)
    (28, 4),   -- Crispy Crusts (restaurant_id: 40) is associated with category_id 5 (Asian Food)
    (29, 6),   -- Comfort Classics (restaurant_id: 41) is associated with category_id 7 (Vegetarian Menu)
    (30, 7)    -- Vietnamese Vibes (restaurant_id: 42) is associated with category_id 8 (Souvlaki)
    ON CONFLICT DO NOTHING;


-- Menu items for "Pizza Place" (restaurant_id: 1)
INSERT INTO efant.menu_items (restaurant_id, name, description, price, image_url)
VALUES
    (1, 'Margherita Pizza', 'Classic tomato, mozzarella, and basil', 9.99, 'item_1_1.jpg'),
    (1, 'Pepperoni Pizza', 'Tomato, mozzarella, and pepperoni', 10.99, 'item_1_2.jpg'),
    (1, 'Vegetarian Pizza', 'Mushrooms, bell peppers, olives, and onions', 11.99, 'item_1_3.jpg'),
    (1, 'Hawaiian Pizza', 'Ham, pineapple, and mozzarella', 12.99, 'item_1_4.jpg'),
    (1, 'Meat Lovers Pizza', 'Pepperoni, sausage, bacon, and ham', 13.99, 'item_1_5.jpg'),
    (1, 'BBQ Chicken Pizza', 'Grilled chicken, BBQ sauce, and red onions', 12.99, 'item_1_6.jpg'),
    (1, 'Supreme Pizza', 'Pepperoni, sausage, mushrooms, bell peppers, and olives', 14.99, 'item_1_7.jpg'),
    (2, 'Classic Burger', 'Beef patty with lettuce and tomato', 8.99, 'item_2_1.jpg'),
    (2, 'Cheeseburger', 'Beef patty with cheese, lettuce, and tomato', 9.99, 'item_2_2.jpg'),
    (2, 'Bacon Burger', 'Beef patty with bacon, cheese, lettuce, and tomato', 10.99, 'item_2_3.jpg'),
    (2, 'Mushroom Swiss Burger', 'Beef patty with mushrooms, Swiss cheese, lettuce, and tomato', 10.99, 'item_2_4.jpg'),
    (2, 'Double Burger', 'Two beef patties with cheese, lettuce, and tomato', 12.99, 'item_2_5.jpg'),
    (3, 'Moussaka', 'Layers of eggplant, potato, and ground beef topped with béchamel sauce', 13.99, 'item_3_1.jpg'),
    (3, 'Souvlaki Platter', 'Grilled skewers of chicken and pork with pita and tzatziki', 14.99, 'item_3_2.jpg'),
    (3, 'Spanakopita', 'Spinach and feta cheese wrapped in flaky phyllo pastry', 10.99, 'item_3_3.jpg'),
    (3, 'Dolmades', 'Grape leaves stuffed with rice and herbs', 8.99, 'item_3_4.jpg'),
    (3, 'Greek Salad', 'Tomatoes, cucumbers, olives, onions, and feta cheese with vinaigrette', 9.99, 'item_3_5.jpg'),
    (4, 'Pad Thai', 'Stir-fried rice noodles with tofu, shrimp, bean sprouts, and peanuts', 12.99, 'item_4_1.jpg'),
    (4, 'Sushi Combo', 'Assortment of nigiri and maki rolls', 15.99, 'item_4_2.jpg'),
    (4, 'Kung Pao Chicken', 'Spicy stir-fried chicken with peanuts and vegetables', 11.99, 'item_4_3.jpg'),
    (4, 'Beef Teriyaki', 'Grilled beef with teriyaki sauce and steamed rice', 13.99, 'item_4_4.jpg'),
    (4, 'Green Curry', 'Spicy Thai curry with coconut milk, vegetables, and choice of meat', 14.99, 'item_4_5.jpg'),
    (5, 'Espresso', 'A shot of strong black coffee', 2.99, 'item_5_1.jpg'),
    (5, 'Cappuccino', 'Espresso with steamed milk and foam', 3.99, 'item_5_2.jpg'),
    (5, 'Latte', 'Espresso with steamed milk', 4.49, 'item_5_3.jpg'),
    (5, 'Mocha', 'Espresso with chocolate and steamed milk', 4.49, 'item_5_4.jpg'),
    (5, 'Iced Coffee', 'Chilled brewed coffee served with ice', 3.49, 'item_5_5.jpg'),
    (6, 'Quinoa Salad', 'Mixed greens, quinoa, cherry tomatoes, and avocado with lemon vinaigrette', 9.99, 'item_6_1.jpg'),
    (6, 'Veggie Wrap', 'Grilled vegetables, hummus, and greens wrapped in a whole wheat tortilla', 8.99, 'item_6_2.jpg'),
    (6, 'Zucchini Noodles', 'Zucchini noodles with pesto sauce and cherry tomatoes', 10.99, 'item_6_3.jpg'),
    (6, 'Falafel Bowl', 'Falafel balls served with tabbouleh, hummus, and tahini sauce', 11.99, 'item_6_4.jpg'),
    (6, 'Stir-Fry Tofu', 'Tofu stir-fried with vegetables and soy sauce', 10.99, 'item_6_5.jpg'),
    (7, 'Chicken Souvlaki Wrap', 'Grilled chicken skewers wrapped in pita with lettuce, tomato, and tzatziki', 9.99, 'item_7_1.jpg'),
    (7, 'Pork Souvlaki Platter', 'Grilled pork skewers served with pita, salad, and tzatziki', 11.99, 'item_7_2.jpg'),
    (7, 'Greek Gyro', 'Thinly sliced gyro meat wrapped in pita with onions, tomato, and tzatziki', 10.99, 'item_7_3.jpg'),
    (8, 'Spaghetti Bolognese', 'Spaghetti with meat sauce', 12.99, 'item_8_1.jpg'),
    (8, 'Chicken Alfredo', 'Grilled chicken with creamy Alfredo sauce and fettuccine', 14.99, 'item_8_2.jpg'),
    (8, 'Eggplant Parmesan', 'Breaded and baked eggplant with marinara sauce and mozzarella', 11.99, 'item_8_3.jpg'),
    (9, 'Beef Tacos', 'Seasoned ground beef in corn tortillas with lettuce, cheese, and salsa', 8.99, 'item_9_1.jpg'),
    (9, 'Chicken Burrito', 'Grilled chicken, rice, beans, lettuce, and cheese wrapped in a flour tortilla', 9.99, 'item_9_2.jpg'),
    (9, 'Veggie Quesadilla', 'Grilled vegetables and cheese folded in a flour tortilla', 7.99, 'item_9_3.jpg'),
    (9, 'Fish Tacos', 'Battered and fried fish in corn tortillas with slaw and lime crema', 10.99, 'item_9_4.jpg'),
    (10, 'California Roll', 'Crab, avocado, and cucumber rolled in sushi rice and seaweed', 11.99, 'item_10_1.jpg'),
    (10, 'Salmon Nigiri', 'Fresh salmon slices on rice balls', 13.99, 'item_10_2.jpg'),
    (10, 'Tuna Sashimi', 'Fresh tuna slices served with pickled ginger and wasabi', 14.99, 'item_10_3.jpg'),
    (10, 'Eel Dragon Roll', 'Grilled eel, avocado, and cucumber topped with eel sauce and tobiko', 15.99, 'item_10_4.jpg'),
    (11, 'Carbonara', 'Spaghetti with pancetta, eggs, and Parmesan cheese', 13.99, 'item_11_1.jpg'),
    (11, 'Penne Arrabbiata', 'Penne pasta with spicy tomato sauce', 11.99, 'item_11_2.jpg'),
    (11, 'Lasagna', 'Layered pasta with meat sauce, ricotta, and mozzarella', 14.99, 'item_11_3.jpg'),
    (12, 'Acai Bowl', 'Acai berries, granola, and fresh fruit', 9.99, 'item_12_1.jpg'),
    (12, 'Buddha Bowl', 'Brown rice, roasted vegetables, and tahini dressing', 10.99, 'item_12_2.jpg'),
    (12, 'Tofu Poke Bowl', 'Marinated tofu, edamame, and avocado over sushi rice', 12.99, 'item_12_3.jpg'),
    (13, 'Grilled Salmon', 'Fresh salmon fillet served with roasted vegetables', 15.99, 'item_13_1.jpg'),
    (13, 'Shrimp Scampi', 'Sauteed shrimp in garlic butter sauce over linguine', 14.99, 'item_13_2.jpg'),
    (13, 'Lobster Roll', 'Chilled lobster salad in a buttered roll', 16.99, 'item_13_3.jpg'),
    (14, 'Eggs Benedict', 'Poached eggs, Canadian bacon, and hollandaise sauce on English muffins', 12.99, 'item_14_1.jpg'),
    (14, 'Avocado Toast', 'Smashed avocado, cherry tomatoes, and feta on toast', 9.99, 'item_14_2.jpg'),
    (14, 'Croissant Sandwich', 'Ham, cheese, and fried egg on a buttery croissant', 10.99, 'item_14_3.jpg'),
    (15, 'BBQ Ribs', 'St. Louis-style ribs glazed with barbecue sauce', 15.99, 'item_15_1.jpg'),
    (15, 'Pulled Pork Sandwich', 'Slow-cooked pulled pork with coleslaw on a bun', 12.99, 'item_15_2.jpg'),
    (15, 'Grilled Chicken Wings', 'Marinated chicken wings with choice of barbecue sauce', 11.99, 'item_15_3.jpg'),
    (16, 'Falafel Wrap', 'Crispy falafel with lettuce, tomato, and tahini sauce in pita', 9.99, 'item_16_1.jpg'),
    (16, 'Chicken Shawarma', 'Grilled chicken shawarma with hummus and pickles in lavash bread', 11.99, 'item_16_2.jpg'),
    (16, 'Baba Ganoush', 'Smoky eggplant dip served with pita bread', 8.99, 'item_16_3.jpg'),
    (17, 'Ribeye Steak', 'Grilled ribeye steak served with mashed potatoes and vegetables', 19.99, 'item_17_1.jpg'),
    (17, 'Filet Mignon', 'Tender filet mignon with red wine reduction sauce', 22.99, 'item_17_2.jpg'),
    (17, 'Surf and Turf', 'Grilled steak and shrimp with garlic butter', 24.99, 'item_17_3.jpg'),
    (18, 'Chicken Tikka Masala', 'Tandoori chicken in creamy tomato sauce', 12.99, 'item_18_1.jpg'),
    (18, 'Vegetable Biryani', 'Basmati rice cooked with mixed vegetables and spices', 11.99, 'item_18_2.jpg'),
    (18, 'Tandoori Naan', 'Traditional Indian bread cooked in a tandoor', 2.99, 'item_18_3.jpg'),
    (19, 'Green Curry Chicken', 'Spicy Thai green curry with coconut milk and vegetables', 13.99, 'item_19_1.jpg'),
    (19, 'Pad See Ew', 'Stir-fried wide rice noodles with broccoli, egg, and soy sauce', 11.99, 'item_19_2.jpg'),
    (19, 'Tom Yum Soup', 'Hot and sour soup with shrimp, mushrooms, and lemongrass', 9.99, 'item_19_3.jpg'),
    (20, 'Coq au Vin', 'Classic French braised chicken with red wine and mushrooms', 16.99, 'item_20_1.jpg'),
    (20, 'Beef Bourguignon', 'Slow-cooked beef stew in red wine with carrots and pearl onions', 18.99, 'item_20_2.jpg'),
    (21, 'Patatas Bravas', 'Fried potatoes with spicy tomato sauce and aioli', 8.99, 'item_21_1.jpg'),
    (21, 'Garlic Shrimp', 'Sautéed shrimp with garlic, olive oil, and chili flakes', 10.99, 'item_21_2.jpg'),
    (21, 'Spanish Tortilla', 'Potato and egg omelette with onions', 9.99, 'item_21_3.jpg'),
    (22, 'Lamb Kebabs', 'Grilled lamb skewers served with rice and salad', 14.99, 'item_22_1.jpg'),
    (22, 'Hummus Platter', 'Creamy hummus with pita, olives, and pickles', 9.99, 'item_22_2.jpg'),
    (23, 'Chicken Tikka', 'Marinated and grilled chicken pieces', 12.99, 'item_23_1.jpg'),
    (23, 'Vegetable Samosa', 'Crispy pastry filled with spiced vegetables', 7.99, 'item_23_2.jpg'),
    (23, 'Butter Chicken', 'Tandoori chicken in a creamy tomato sauce', 13.99, 'item_23_3.jpg'),
    (24, 'Jerk Chicken', 'Grilled chicken marinated in jerk spices', 12.99, 'item_24_1.jpg'),
    (24, 'Rice and Peas', 'Coconut rice with kidney beans', 8.99, 'item_24_2.jpg'),
    (25, 'Steak and Ale Pie', 'Tender steak in ale gravy with puff pastry', 14.99, 'item_25_1.jpg'),
    (25, 'Chicken Pot Pie', 'Creamy chicken and vegetable filling with flaky crust', 12.99, 'item_25_2.jpg'),
    (25, 'Vegetable Pie', 'Mixed vegetables in a savory pastry crust', 10.99, 'item_25_3.jpg'),
    (26, 'Vegan Burger', 'Plant-based patty with lettuce, tomato, and vegan mayo', 9.99, 'item_26_1.jpg'),
    (26, 'Vegan Pad Thai', 'Stir-fried rice noodles with tofu and vegetables', 11.99, 'item_26_2.jpg'),
    (26, 'Vegan Pizza', 'Dairy-free cheese and assorted vegetables on a thin crust', 12.99, 'item_26_3.jpg'),
    (27, 'Rainbow Roll', 'Assorted fish and avocado on California roll', 16.99, 'item_27_1.jpg'),
    (27, 'Spider Roll', 'Soft-shell crab, avocado, and cucumber', 14.99, 'item_27_2.jpg'),
    (28, 'Chicken Enchiladas', 'Corn tortillas filled with chicken and topped with enchilada sauce', 13.99, 'item_28_1.jpg'),
    (28, 'Chili Con Carne', 'Spicy beef and bean chili with cheese and sour cream', 12.99, 'item_28_2.jpg'),
    (28, 'Guacamole and Chips', 'Fresh avocado dip served with tortilla chips', 8.99, 'item_28_3.jpg'),
    (29, 'Croissant', 'Buttery and flaky French pastry', 3.99, 'item_29_1.jpg'),
    (29, 'Chocolate Eclair', 'Choux pastry filled with custard and topped with chocolate', 4.99, 'item_29_2.jpg'),
    (29, 'Fruit Tart', 'Pastry crust filled with pastry cream and fresh fruit', 5.99, 'item_29_3.jpg'),
    (30, 'Truffle Burger', 'Beef patty with truffle mayo, mushrooms, and arugula', 15.99, 'item_30_1.jpg'),
    (30, 'Blue Cheese Burger', 'Beef patty with blue cheese, bacon, and caramelized onions', 14.99, 'item_30_2.jpg'),
    (30, 'Veggie Burger', 'Plant-based patty with lettuce, tomato, and vegan cheese', 13.99, 'item_30_3.jpg')
    ON CONFLICT DO NOTHING;



