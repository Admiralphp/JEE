# Student Management API

A comprehensive Spring Boot application demonstrating various Spring Boot features and best practices. This project is part of a Spring Boot course covering essential concepts from basic web applications to advanced caching implementations.

## Course Information
- **Course Name:** Développement JEE
- **Course Link:** [https://docs.teknolabs.net/courses/jee/](https://docs.teknolabs.net/courses/jee/)
- **Teacher:** Wahid Hamdi
- **Class:** M1 DevOps & Cloud - 2024/2025

## Author
- **Mohamed ESSID**
- Email: mohamedessid2200@gmail.com
- GitHub: [Admiralphp](https://github.com/Admiralphp)

## Course Structure

### Module 1: Introduction & Environment Setup
- Basic Spring Boot configuration
- Hello World application
- Project structure setup

### Module 2: Building Web Applications & MVC
- Spring MVC implementation
- Thymeleaf templating
- Student Portal web interface
- Form handling and validation

### Module 3: Creating RESTful Web Services
- RESTful API implementation
- HTTP methods (GET, POST, PUT, DELETE)
- OpenAPI/Swagger documentation
- Spring Boot Actuator monitoring

### Module 4: Data Persistence with Spring Data JPA
- JPA entity management
- Repository pattern
- Database migrations with Flyway
- Transaction management

### Module 5: Redis Caching
- Redis cache integration
- Cache configuration
- Performance optimization
- Cache management strategies

## Technology Stack

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Spring MVC with Thymeleaf
- Spring Data Redis (Caching)
- H2 Database (In-memory)
- Flyway (Database Migration)
- OpenAPI/Swagger (API Documentation)
- Spring Boot Actuator (Monitoring)
- Maven (Build Tool)

## Modules Overview

### 1. Core Module (Base Spring Boot Application)
- Location: `src/main/java/com/teknolabs/student_api/`
- Main application configuration and bootstrap
- Handles dependency injection and component scanning

### 2. Data Access Layer
- Location: `src/main/java/com/teknolabs/student_api/repository/`
- JPA repositories for database operations
- Custom query methods
- Database entity mappings

### 3. Service Layer
- Location: `src/main/java/com/teknolabs/student_api/service/`
- Business logic implementation
- Transaction management
- Caching implementation with Redis

### 4. Web Layer
- Location: `src/main/java/com/teknolabs/student_api/controller/`
- REST API endpoints
- Thymeleaf web interface controllers
- Request/Response handling

### 5. Model Layer
- Location: `src/main/java/com/teknolabs/student_api/model/`
- Entity definitions
- Data validation rules
- DTO classes

### 6. Configuration
- Location: `src/main/java/com/teknolabs/student_api/config/`
- Redis caching configuration
- JPA configuration
- OpenAPI documentation setup

### 7. Database Migration
- Location: `src/main/resources/db/migration/`
- Flyway migration scripts
- Database schema evolution
- Sample data initialization

## Project Structure
```
student-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── teknolabs/
│   │   │           └── student_api/
│   │   │               ├── config/
│   │   │               │   ├── JpaConfig.java
│   │   │               │   ├── OpenApiConfig.java
│   │   │               │   └── RedisConfig.java
│   │   │               ├── controller/
│   │   │               │   └── StudentController.java
│   │   │               ├── exception/
│   │   │               │   ├── ResourceNotFoundException.java
│   │   │               │   └── RestExceptionHandler.java
│   │   │               ├── model/
│   │   │               │   ├── ErrorResponse.java
│   │   │               │   ├── Student.java
│   │   │               │   └── StudentStatus.java
│   │   │               ├── repository/
│   │   │               │   └── StudentRepository.java
│   │   │               ├── service/
│   │   │               │   └── StudentService.java
│   │   │               └── StudentApiApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── db/
│   │       │   └── migration/
│   │       │       ├── V1__Create_students_table.sql
│   │       │       └── V2__Insert_sample_data.sql
│   │       ├── static/
│   │       └── templates/
│   │           ├── layout/
│   │           │   └── main.html
│   │           └── students/
│   │               ├── form.html
│   │               └── list.html
│   └── test/
│       └── java/
│           └── com/
│               └── teknolabs/
│                   └── student_api/
│                       └── StudentApiApplicationTests.java
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Redis server (Memurai for Windows)

## Getting Started

1. Clone the repository:
```bash
git clone <repository-url>
cd student-api
```

2. Make sure Redis is running (using Memurai on Windows)

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## Available Endpoints

### Web Interface URLs

1. Student List Page:
```
http://localhost:8080/students
```

2. Add New Student Form:
```
http://localhost:8080/students/new
```

3. Edit Student Form:
```
http://localhost:8080/students/{id}/edit
```

### REST API Endpoints

1. Student Management:
- GET `/api/students` - List all students
- GET `/api/students/{id}` - Get a specific student
- POST `/api/students` - Create a new student
- PUT `/api/students/{id}` - Update a student
- DELETE `/api/students/{id}` - Delete a student

Example POST/PUT request body:
```json
{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "dateOfBirth": "2000-01-01",
    "phoneNumber": "+1234567890",
    "status": "ACTIVE"
}
```

### API Documentation
```
http://localhost:8080/swagger-ui.html
```

### Monitoring Endpoints (Actuator)
```
http://localhost:8080/actuator/health    # Health information
http://localhost:8080/actuator/info      # Application information
http://localhost:8080/actuator/metrics   # Application metrics
```

### Database Console
```
http://localhost:8080/h2-console
```
Connection details:
- JDBC URL: `jdbc:h2:mem:studentdb`
- Username: `sa`
- Password: (leave empty)

## Caching

The application uses Redis for caching frequently accessed data. Cache configuration includes:
- TTL: 60 minutes
- Cache key prefix: "student-api:"
- Cached operations: findById, findAll, findByStatus

## Testing

Run the tests using:
```bash
mvn test
```

## Configuration Properties

Key application properties (in `application.properties`):
```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:studentdb
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# Redis Cache
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## Error Handling

The application includes global error handling for:
- Resource not found (404)
- Validation errors (400)
- Server errors (500)

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## Project Repository

This project is part of the JEE course and can be found at:
[https://github.com/Admiralphp/JEE/tree/main/MODULE-1-5/M1/student-api](https://github.com/Admiralphp/JEE/tree/main/MODULE-1-5/M1/student-api)

## License

MIT License

Copyright (c) 2025 Mohamed ESSID

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.