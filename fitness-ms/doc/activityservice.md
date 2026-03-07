# Activity Service

## Overview
| Property | Value |
|----------|-------|
| Port | 8082 |
| Purpose | Record and list user activities |
| Database | MongoDB (Atlas) |
| Technology | Spring Boot, Spring Data MongoDB, RabbitMQ, WebClient |

## REST Endpoints
| Method | Path | Header | Description |
|--------|------|--------|-------------|
| `POST` | `/api/activities` | — | Track a new activity |
| `GET` | `/api/activities` | `X-User-Id` | Get all activities for a user |
| `GET` | `/api/activities/{activityId}` | — | Get a specific activity |

### POST /api/activities — Request Body
```json
{
  "userId": "6d670f75-696d-4411-a927-29ac84d04632",
  "type": "RUNNING",
  "duration": 30,
  "caloriesBurned": 300,
  "startTime": "2026-03-07T10:00:00",
  "additionalMetrics": {
    "distance": "5km",
    "pace": "6min/km"
  }
}
```

## Entity
### Activity (MongoDB Document)
| Field | Type | Description |
|-------|------|-------------|
| id | String | MongoDB ObjectId |
| userId | String | User ID |
| type | ActivityType | Type of activity |
| duration | int | Duration in minutes |
| caloriesBurned | int | Calories burned |
| startTime | LocalDateTime | Activity start time |
| additionalMetrics | Map<String, Object> | Extra data |
| createdAt | LocalDateTime | Creation timestamp |
| updatedAt | LocalDateTime | Last update timestamp |

### ActivityType (Enum)
`RUNNING`, `WALKING`, `CYCLING`, `SWIMMING`, `WEIGHT_TRAINING`, `YOGA`, `CARDIO`, `STRENGTHENING`, `OTHER`

## Package Structure
```
webApi/
  ActivityController           → REST endpoints
business/
  abstracts/
    ActivityService            → Activity service interface
    UserValidateService        → User validation interface
  concretes/
    ActivityServiceManager     → Activity business logic
    UserValidateServiceManager → Calls UserService via WebClient
dataAccess/
  ActivityRepository           → MongoDB queries
entities/
  Activity                     → MongoDB document
  ActivityType (enum)
config/
  RabbitMqConfig               → Exchange, Queue, Binding definitions
  WebClientConfig              → Load-balanced WebClient bean
  MongoConfig                  → MongoDB auditing
```

## RabbitMQ
| Property | Value |
|----------|-------|
| Exchange | `fitness.exchange` (TopicExchange) |
| Queue | `activity.queue` (durable) |
| Routing Key | `activity.tracking` |
| Role | **Producer** — publishes a message when an activity is saved |

## Service Relations
- **UserService** → validates `userId` before saving an activity (load-balanced WebClient)
- **AIService** → receives activity events via RabbitMQ
- **Eureka** → discovers `userservice` by name
