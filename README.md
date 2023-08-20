# E-Fant App

Welcome to the E-Fant app repository! This project utilizes React, Spring Boot, and PostgreSQL to create an online food delivery platform. Users can browse restaurants, view items, and place orders.

## Features

- Browse restaurants and their items
- User authentication and signup
- Place and manage orders


## Technologies Used

### Frontend
- React: Frontend UI framework
- HTML: HyperText Markup Language for webpage structure
- CSS: Cascading Style Sheets for styling

### Backend
- Spring Boot: framework that simplifies the development of Java applications
- Java: high-level, object-oriented programming language
- Maven: build automation and project management tool in Java development

### Database
- PostgreSQL: open-source relational database management system (RDBMS)

  

## Getting Started

### Prerequisites
Before running the application, ensure that you have the following software installed:
- Node.js
- npm
- postgreSQL v.15

### Usage
To run the application, follow these steps:
1. Run the app to initialize schema creation: 
   - Open a new terminal.
   - Navigate to the root of the project where the pom.xml of the parent project lives: `cd {CLONED_DIRECTORY_PATH}\e-fant\`
   - Run the command: `mvn spring-boot:run`
  
2. Set up the database locally:
    - After installing PostGreSQL server and the service is running on your machine (Windows may need restart)
   set the PATH enviroment variables to include: `{POSTGRESQL_INSTALLATION_DIRECTORY}\{VERSION}\bin`
    - Then navigate to path:`{CLONED_DIRECTORY_PATH}/src/main/resources/migrations/`
    - Open a terminal and run: `psql -U postgres -d postgres -a -f V1_efant_db_schema.sql -W`
  
3. Restart the backend server:
close running application and run again the command: `mvn spring-boot:run`  

4. Start the frontend development server:
    - Open a new terminal.
    - Navigate to the frontend application: `cd {CLONED_DIRECTORY_PATH}\e-fant\e-fant-frontend`
    - NOTE: only for the first time you are going to run this app also run the command: `npm install`
    - Run the command: `npm start` 
5. .Access the application in your web browser at: `http://localhost:3000`

Clone the repository:
git clone https://github.com/gyfantidis/e-fant.git

## API Documentation Example
The API's provides the following endpoints:

![image](https://github.com/gyfantidis/e-fant/assets/96373640/20ba4b06-df9b-4e31-8a99-cd49af0fb32f)
API Documentation

## JSON Responses

The Json's responses

![image](https://github.com/gyfantidis/e-fant/assets/96373640/3d602bc0-e80c-4c47-824e-86ce47c91adf)




## Screenshots

<img src="src/screenShots/Screenshot2.png" alt="Home Page" width="600"/>\
Home Page

<img src="src/screenShots/Screenshot10.png" alt="Categorys" width="600"/>\
Restaurant categories

<img src="src/screenShots/Screenshot11.png" alt="Restaurants" width="600"/>\
Restaurants 

<img src="src/screenShots/Screenshot3.png" alt="Log in" width="600"/>\
Log in Page

<img src="src/screenShots/Screenshot4.png" alt="Profile" width="600"/>\
Users profile Page

<img src="src/screenShots/Screenshot8.png" alt="Sign up" width="600"/>\
Sign up Page

<img src="src/screenShots/Screenshot9.png" alt="Address" width="600"/>\
Create new user's address Page

<img src="src/screenShots/Screenshot5.png" alt="Restaurants Items" width="600"/>\
Restaurants Item

<img src="src/screenShots/Screenshot6.png" alt="Order List" width="600"/>\
Orders List

<img src="src/screenShots/Screenshot7.png" alt="Final List" width="600"/>\
Final List and confirm order










