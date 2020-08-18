# Microservice-Security JWT(Json Web Token)
This is a payment management system where users can keep track of all the payments done by them. This Project displays how spring security can be used along with JWT(Json Web Tokens) to provide security to any project build using microservice architecture.

# Structure
The project consists of four microservices.
-	Auth (Spring Boot)
-	User (Spring Boot)
-	Payment (Spring Boot)
-	Payment-Jwt-UI (Angular 7 UI)

Auth, User and Payment are spring Boot application. Payment-Jwt-UI is angular7 application for the UI.

# Database Setup and Structure: -
-	Install MySql and make it run on its default port of 3306. Give the username and pasword, both as `root` to access the Database.
-	create a database payment_db using the following command in the MySql
```sql
	CREATE DATABASE PAYMENT_DB;
```
-	Two tables namely, User and Payment gets created in the payment_db database on running of any one of the backend microservice.
- User table has one to many relationship with the Payment table.
-	Sql queries for creation of table are in Update mode, i.e if the tables already exist then it will not create.
-	Sql queries are available in schema.sql file in the Auth,User and payment microservice under src/main/resources. 

# Payment-Jwt-UI
-	Install latest version of node js from `https://nodejs.org/en/download/` if not done already
-	Run `npm install`  to install all the node modules 
-	Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
```
npm install
ng serve
```


# Spring Boot Microservices details: -
-	User and Payment microservices have inbuilt Swagger attached to them.
-	User Microservice runs on port 8080. Swagger of it can be accessed by the url `http://localhost:8080/swagger-ui.html#/`
-	Payment Microservice runs on port 8085. Swagger of it can be accessed by the url `http://localhost:8085/swagger-ui.html#/`
-	Auth Microservice runs on port 8082.

# User Microservice: -
-	Any User can do Signup. He needs to select a role of admin or user while signing up.  For admin, he needs to set ROLE_ADMIN, while for user he needs to set ROLE_USER.
-	Registered Users can login to system. While login, the Auth service will generate a JWT(Json Web Token). This JWT can be the used to perform any other functionality. To access any other api you need to send a JWT token in the Authorization header. To do it through the swagger-UI you can use the below example.
<img src="https://github.com/DellTechConsulting/microservice-security/blob/master/JWT/SwaggerImage.png" >
 


