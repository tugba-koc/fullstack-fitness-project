# Fitness Microservices

Fitness tracking application — Spring Boot based microservices architecture.

## Services

| Service | Port | Description | Docs |
|---------|------|-------------|------|
| eureka | 8761 | Service registry and discovery | [eureka.md](eureka.md) |
| userservice | 8081 | User registration and profile | [userservice.md](userservice.md) |
| activityservice | 8082 | Activity tracking | [activityservice.md](activityservice.md) |
| aiservice | 8083 | AI-powered recommendations | [aiservice.md](aiservice.md) |

## Architecture

```
Client
  ├── POST /api/users/register       → UserService (8081)
  ├── GET  /api/users/{id}           → UserService (8081)
  │
  ├── POST /api/activities           → ActivityService (8082)
  │     └── validate userId         → UserService (WebClient)
  │     └── publish event           → RabbitMQ
  │                                       └── AIService (8083) listens
  │
  └── GET  /api/recommendations/...  → AIService (8083)

All services → Eureka (8761)
```

## RabbitMQ Flow

```
ActivityService ──[fitness.exchange]──[activity.tracking]──► activity.queue ──► AIService
   (Producer)          (Exchange)          (Routing Key)        (Queue)          (Consumer)
```

## Startup Order

1. **Eureka** `(8761)` — must start first
2. **UserService** `(8081)`
3. **ActivityService** `(8082)`
4. **AIService** `(8083)`

## Technology Stack

| Technology | Usage |
|------------|-------|
| Spring Boot 3.5.11 | Application framework |
| Spring Cloud 2024.0.0 | Eureka client/server |
| Spring Data MongoDB | ActivityService, AIService database |
| Spring Data JPA + PostgreSQL | UserService database |
| RabbitMQ (AMQP) | Inter-service messaging |
| Netflix Eureka | Service discovery |
| WebClient (load-balanced) | Inter-service HTTP calls |
| Lombok | Boilerplate reduction |
