# restaurantFoodOrderingSystem

## Online Restaurant Food Ordering System is a Java Spring Boot web application that allows customers to place food orders online and admins (using admin dashboard) to manage food items.

## Features:

 For Admin: 
-	Register, login, logout
-	Add new meals, edit, delete
-	Update menu
-	View all orders


For Customer:
-	Register, login, logout
-	Manage user profile information
-	View menu, choose meals
-	Place items in the cart
-	Edit the cart
- Checkout order
- Place order
- View order history

## Technologies Used:
- HTML - for web page structure
- CSS - for web page styling
- Java Spring Boot - for backend logic
- Thymeleaf - for connecting backend and frontend functionality
- MySQL - for database

## Project screenshots:


## Setup Instructions:

* Import the project into your preferred Java IDE.
* Update the database connection details in the application.properties, including the username and password for your MySQL server. 
  The spring.jpa.hibernate.ddl-auto property is set to "create" to automatically create the database tables when the application starts.
```
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/foodOrderingSystem?serverTimezone=UTC&createDatabaseIfNotExist=true
server.port=2030

```
* After creating database, change spring.jpa.hibernate.ddl-auto property "create" to "update"

```
spring.jpa.hibernate.ddl-auto=update

```
* Build and run the project on a local server (e.g., Tomcat).
* Access the web application in your web browser using the URL provided by your local server (http://localhost:2030).
* Create a new account for Customer and Admin to explore and use the Food Ordering System.

  http://localhost:2030
  
  http://localhost:2030/adminRegister

  http://localhost:2030/adminLogin

* In order to use all functionality you should use Admin dashboard and add new food items to the system. Once the new food item is added, it will be available for customers to view and order through the customer page.




Note: This is a Java Bootcamp learning project, the main focus is on backend development rather than frontend.
Therefore, the webpage may not be fully responsive as it is not the primary focus of the project. 

