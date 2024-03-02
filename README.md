# Bookstore Management System

## Introduction
The "Bookstore Management" project is a Spring Boot-based web application designed to facilitate efficient inventory management for bookstores. This platform enables users to browse, add items to their cart, and place orders. Following the MVC architecture, the project incorporates robust security measures for user authentication and authorization.

## Project Structure
The project adopts a well-organized directory structure, promoting code maintainability and scalability.

## Configuration
The `SecurityConfig` class ensures secure authentication and authorization for different user roles.

## Controllers
- **BookOperationController:** Manages book-related operations such as viewing, adding, updating, and deleting books.
- **CartController:** Handles cart-related actions, including adding/removing items and viewing cart contents.
- **OrderController:** Manages user orders, allowing users to place new orders and view order history.
- **AuthController:** Manages authentication and registration processes.
- **MainController:** Handles main application views and navigation.

## DTO (Data Transfer Objects)
DTOs, such as `AdminRegistration` and `RegistrationRequest`, facilitate secure data transfer between the front-end and backend.

## Event Handling
The `RegistrationCompleteEvent` class responds to events triggered upon user registration completion.

## Exceptions
The `UserAlreadyExistsException` provides a user-friendly response when a user account already exists during registration.

## Models (Entities)
- **Book:** Represents book information.
- **CartItem:** Represents items in the shopping cart.
- **OrderItem:** Represents items within an order.
- **UserOrder:** Represents user order details.
- **VerificationToken:** Manages email verification tokens.
- **User:** Holds user information.
- **UserRegistrationDetails:** Holds additional user registration details.

## Repositories
Repository interfaces (e.g., `BookRepository`, `CartItemRepository`, `OrderRepository`) provide methods for interacting with the database.

## Services
Service classes (e.g., `BookService`, `CartService`, `OrderService`, `UserService`) contain the business logic for different functionalities.

## Resources
- The `images` directory holds image resources.
- The `templates` directory contains Thymeleaf templates for rendering views.
- The `application.yml` file includes application configuration properties.

## Functionality and Features
- **Book Management:** Admins can add, update, and delete books.
- **User Cart:** Users can add and remove items from their shopping cart.
- **Order Placement:** Users can place orders and view order history.
- **User Authentication:** Secure registration and login processes.
- **Email Verification:** Mandatory for user registration, with a verification token sent via console. Users are enabled after verification.
- **Role-based Access Control:** Admins and users have distinct access levels.

## User-Friendly Interface
Thymeleaf templates with Bootstrap create well-designed, user-friendly views.

## Conclusion
The "Bookstore Management System" is a comprehensive Spring Boot application, demonstrating the practical application of Java for building web-based systems. Its structured architecture, security features, and user-friendly interface contribute to a seamless and secure user experience.