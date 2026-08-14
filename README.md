# 🎬 BookMyShow Backend API

A robust RESTful backend application for a Movie Ticket Booking System (BookMyShow clone) built using **Spring Boot, Spring Security, JWT, MySQL, and Hibernate/JPA**. 

This project provides a secure, scalable architecture for managing movies, theatres, screen configurations, show scheduling, and seat bookings with concurrency handling.

## 🚀 Features

### 🔐 Authentication & Security
- User Sign up and Sign in (Authentication)
- JWT (JSON Web Token) based stateless authentication
- Spring Security configuration
- Role-Based Access Control (RBAC)
- Protected REST endpoints

### 🎥 Movie & Theatre Management
- Add and manage Movies (Title, Genre, Language, Duration)
- Add Theatres along with multiple Screens
- Seat layout configuration for each screen (Gold, Silver, etc.)

### 🎟️ Show & Booking Management
- Schedule movie shows by linking Movies and Screens with custom timings and pricing
- Check available seats dynamically for any scheduled show
- Book multiple tickets/seats in a single transaction
- Cancel bookings with automatic seat status restoration (`AVAILABLE`)

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- JWT (JSON Web Tokens)
- REST APIs

### Database
- MySQL

### Tools & Testing
- IntelliJ IDEA
- Postman (API Testing & Documentation)
- Maven
- Git & GitHub

## 📁 Project Structure

bookmyshow-project/
│
├── bookmyshow-backend/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
└── README.md
