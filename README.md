# E-Commerce Backend with Spring Boot

**E-Commerce Backend** is an open-source project that provides a robust backend system for an online shopping platform. Developed using Java and the Spring Boot framework, this application handles essential functionalities such as user authentication, product management, order processing, and more.

## Features

- **User Authentication**: Secure user registration and login system with role-based access control.
- **Product Management**: Add, update, and delete products with detailed information.

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: SQLServer
- **Authentication**: JSON Web Tokens (JWT)
- **Build Tool**: Maven

## Installation

To set up the E-Commerce Backend project locally, follow these steps:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/burakpekisik/ecommerce-backend-springboot.git
   ```

2. **Navigate to the Project Directory**:

   ```bash
   cd ecommerce-backend-springboot
   ```

3. **Configure the Database**:

   - Ensure you have MySQL installed and running.
   - Create a new database named `ecommerce_db`.
   - Update the database connection details in `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

4. **Build and Run the Application**:

   - Use Maven to build and run the application:

     ```bash
     mvn clean install
     mvn spring-boot:run
     ```

   The application will start on `http://localhost:8080`.

## Usage

1. **Access the API**: The backend API is accessible at `http://localhost:8080`.
2. **Authentication**: Use the `/login` endpoint to obtain a JWT for secure access to protected routes.
3. **Admin Panel**: Manage users, products, and orders through the admin dashboard.

## Disclaimer

This project is intended for educational purposes and may require further development for production use. The maintainers are not responsible for any misuse or unintended consequences arising from the use of this software. 
