# User Service

## Overview
| Property | Value |
|----------|-------|
| Port | 8081 |
| Purpose | User registration, profile management, and validation |
| Database | PostgreSQL (`fitness-user-db`) |
| Technology | Spring Boot, Spring Data JPA |

## REST Endpoints
| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/api/users/register` | Register a new user |
| `GET` | `/api/users/{userId}` | Get user profile |
| `GET` | `/api/users/{userId}/validate` | Check if a user exists (returns `boolean`) |

### POST /api/users/register — Request Body
```json
{
  "email": "user@example.com",
  "password": "min6chars",
  "firstName": "John",
  "lastName": "Doe"
}
```

## Entity
### User
| Field | Type | Description |
|-------|------|-------------|
| id | UUID | Primary key |
| email | String (unique) | User email |
| password | String | Password |
| firstName | String | First name |
| lastName | String | Last name |
| role | UserRole | `USER` or `ADMIN` |
| createdAt | LocalDateTime | Creation timestamp |
| updatedAt | LocalDateTime | Last update timestamp |

## Package Structure
```
webApi/
  UserController           → REST endpoints
business/
  abstracts/
    UserService            → Interface
  concretes/
    UserServiceManager     → Business logic
dataAccess/
  UserRepository           → JPA queries
entities/
  User                     → Database entity
  UserRole (enum)          → USER, ADMIN
```

## Service Relations
- **ActivityService** → calls `/api/users/{userId}/validate` via WebClient
- **Eureka** → registers under the name `userservice`
