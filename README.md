# FreshTrack Backend

FreshTrack is a Smart Grocery and Food Waste Management System backend built with Java and Spring Boot.

## Tech Stack
- **Java 17+**
- **Spring Boot** (Spring Web, Spring Data JPA)
- **MySQL Database**
- **Maven**

## Features
- Track grocery items and inventory
- Expiration date monitoring and food waste prevention
- REST APIs for grocery items management (CRUD operations)
- Cloud deployment configuration (Railway support)

## Getting Started

### Prerequisites
- JDK 17 or higher
- MySQL Server
- Maven (or use the included `./mvnw`)

### Configuration
1. Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties`.
2. Update your database connection details in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/freshtrack_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run Locally
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```
The server will start at `http://localhost:8080`.
