# AI Service

## Overview
| Property | Value |
|----------|-------|
| Port | 8083 |
| Purpose | Generate AI-powered fitness recommendations |
| Database | MongoDB (Atlas) |
| Technology | Spring Boot, Spring Data MongoDB, RabbitMQ |

## REST Endpoints
| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/api/recommendations/user/{userId}` | Get all recommendations for a user |
| `GET` | `/api/recommendations/activity/{activityId}` | Get recommendation for a specific activity |

## Entity
### Recommendation (MongoDB Document)
| Field | Type | Description |
|-------|------|-------------|
| id | String | MongoDB ObjectId |
| userId | String | User ID |
| activityId | String | Activity ID |
| activityType | String | Type of activity |
| recommendation | String | General recommendation text |
| improvements | List\<String\> | Improvement suggestions |
| suggestions | List\<String\> | Activity-specific advice |
| safety | List\<String\> | Safety warnings |
| createdAt | LocalDateTime | Creation timestamp |

## Package Structure
```
webApi/
  RecommendationController       → REST endpoints
business/
  abstracts/
    RecommendationService        → Recommendation service interface
  concretes/
    RecommendationManager        → Business logic
  listener/
    ActivityMessageListener      → RabbitMQ message listener
dataAccess/
  RecommendationRepository       → MongoDB queries
entities/
  Recommendation                 → MongoDB document
  Activity                       → POJO for RabbitMQ deserialization
config/
  RabbitMqConfig                 → Exchange, Queue, Binding definitions
  MongoConfig                    → MongoDB auditing
```

## RabbitMQ
| Property | Value |
|----------|-------|
| Exchange | `fitness.exchange` (TopicExchange) |
| Queue | `activity.queue` (durable) |
| Routing Key | `activity.tracking` |
| Role | **Consumer** — listens for activity events |

### Message Listener
```java
@RabbitListener(queues = "activity.queue")
public void receiveActivityMessage(Activity activity) {
    // Generates AI recommendation based on the received activity
}
```

## Service Relations
- **ActivityService** → receives activity events via RabbitMQ
- **Eureka** → registers as a service
