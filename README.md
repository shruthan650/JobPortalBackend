# Job Portal Backend

A RESTful Job Portal backend built with **Java, Spring Boot, Spring Security, and MongoDB**.

## Features

- RESTful CRUD APIs for jobs, companies, users, and applications
- MongoDB persistence
- Spring Security authentication and authorization
- Role-based access: `ADMIN`, `RECRUITER`, `CANDIDATE`
- Method-level authorization with `@PreAuthorize`
- Resource ownership checks
- Bean Validation with `@Valid`, `@NotBlank`, and related constraints
- Custom `ResourceNotFoundException` and `InvalidInputException`
- Global exception handling with `@ControllerAdvice`
- SLF4J logging
- Application status enum and controlled status transitions

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Security
- Spring Data MongoDB
- MongoDB
- Jakarta Bean Validation
- Maven
- SLF4J

## Domain Models

### JobPost

Represents a job posted by a recruiter. It includes information such as title, description, profile, experience, technologies, location, salary, company, recruiter, and creation date.

### Company

Represents a company associated with job postings.

### User

Represents candidates, recruiters, and administrators. The user's role determines which operations they can perform.

### Application

Represents a candidate's application for a job.

Fields include:

- Application ID
- Job ID
- Candidate ID
- Applied date
- Application status

## Security

Authorization is enforced with Spring Security and `@PreAuthorize`.

The application distinguishes between **role authorization** and **resource ownership**.

For example, a recruiter should not be able to modify another recruiter's job or application merely because both users have the `RECRUITER` role.

Public job retrieval can be exposed without authentication, while protected operations require the appropriate role and/or ownership.

## Validation

Incoming request bodies use Bean Validation.

Typical rules include:

- Required strings must not be blank.
- Required numeric values must not be null.
- Required collections should not be empty where appropriate.

Controllers use `@Valid` so invalid request data is rejected before normal service processing.

Invalid requests result in `400 Bad Request`.

## Exception Handling

### `ResourceNotFoundException`

Used when a requested resource does not exist.

Example:

```text
GET /api/application/999
→ 404 NOT FOUND
```

### `InvalidInputException`

Used when input violates a business rule, such as:

- IDs do not match
- Immutable application fields are changed
- An invalid application-status transition is requested

These result in `400 Bad Request`.

### `GlobalExceptionHandler`

`@ControllerAdvice` handles exceptions globally:

```text
Controller / Service
        ↓
Exception
        ↓
GlobalExceptionHandler
        ↓
HTTP response
```

## Logging

SLF4J logging is used in service and exception-handling layers for useful operational events, warnings, and errors.

Sensitive information such as passwords and authentication tokens should never be logged.

## MongoDB Configuration

Configure MongoDB in `application.properties` or `application.yml`.

For local MongoDB, an example is:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/jobportal
```

For MongoDB Atlas, use the Atlas connection string.

**Never commit database credentials, JWT secrets, or other sensitive configuration to Git.**

## Running the Project

### Prerequisites

- Java
- Maven or the Maven wrapper
- Local MongoDB or MongoDB Atlas
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code

### Start

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class from your IDE.

The default server address is normally:

```text
http://localhost:8080
```

## API Overview

The application provides APIs for:

```text
Jobs
Companies
Users
Applications
```

Example:

```text
GET /api/jobs
```

Job retrieval can be public, while create/update/delete operations are protected according to the configured authorization rules.

Application APIs support creating, retrieving, updating, and deleting applications subject to authentication, role, and ownership rules.

## Application Workflow

### Candidate creates an application

```text
Candidate
   ↓
Create application
   ↓
Backend sets status = APPLIED
   ↓
Backend sets appliedAt
   ↓
Save to MongoDB
```

## Error Responses

### Not Found

```text
HTTP 404 NOT FOUND
```

Example:

```text
Job Application Not Found
```


## Testing

The APIs are tested with Postman.

Recommended tests:

- Successful CRUD operations
- Invalid request data
- Missing resources
- Unauthorized users
- Ownership violations
- Valid status transitions
- Invalid status transitions
- Attempts to modify immutable application fields

## Project Structure

A typical structure is:

```text
src/main/java/
└── <base-package>/
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    ├── exception/
    └── security/
```

## Design Principles

- **Controllers** handle HTTP requests and authorization.
- **Services** contain business logic.
- **Repositories** handle database persistence.
- **Validation** protects the API boundary.
- **Exceptions** represent failure conditions.
- **`@ControllerAdvice`** converts exceptions into HTTP responses.
- **Enums** represent fixed sets of states.
- **Ownership checks** prevent users with the same role from accessing each other's resources.

## Author

Shruthan650
