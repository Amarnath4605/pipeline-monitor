# pipeline-monitor
CI/CD Pipeline Monitor REST API built with Spring Boot
# Pipeline Monitor - CI/CD Pipeline Monitoring REST API

## Tech Stack
- Java 21
- Spring Boot
- Spring Security + JWT
- MySQL
- Maven

## Features
- User Registration & Login with JWT Authentication
- Password Encryption using BCrypt
- Pipeline CRUD Operations
- Filter Pipelines by Status
- RESTful API Design

## API Endpoints

### Auth
- POST /api/auth/register - Register new user
- POST /api/auth/login    - Login and get JWT token

### Pipelines
- POST   /api/pipelines              - Create pipeline
- GET    /api/pipelines              - Get all pipelines
- GET    /api/pipelines/{id}         - Get pipeline by ID
- PUT    /api/pipelines/{id}/status  - Update pipeline status
- GET    /api/pipelines/status/{status} - Filter by status
- DELETE /api/pipelines/{id}         - Delete pipeline

## How to Run
1. Clone the repo
2. Create MySQL database: CREATE DATABASE pipelinedb;
3. Update application.properties with your MySQL password
4. Run PipelineMonitorApplication.java
