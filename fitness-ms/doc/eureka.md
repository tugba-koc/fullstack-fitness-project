# Eureka Server

## Overview
| Property | Value |
|----------|-------|
| Port | 8761 |
| Purpose | Service registry and discovery |
| Technology | Spring Cloud Netflix Eureka Server |

## What Does It Do?
Acts as a central directory so all microservices can find each other. Each service registers with Eureka on startup. When a service needs to call another service, it looks up the IP/port from Eureka.

## Configuration
```yaml
eureka:
  client:
    register-with-eureka: false   # Does not register itself
    fetch-registry: false         # Does not fetch the registry
```

## Registered Services
| Service | Port |
|---------|------|
| userservice | 8081 |
| activityservice | 8082 |
| aiservice | 8083 |

## Dashboard
Once running, visit `http://localhost:8761` to see all registered services.
