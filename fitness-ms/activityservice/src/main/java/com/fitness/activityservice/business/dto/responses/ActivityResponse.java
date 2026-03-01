package com.fitness.activityservice.business.dto.responses;

import java.time.LocalDateTime;
import java.util.Map;

import com.fitness.activityservice.entities.enums.ActivityType;

import lombok.Data;

@Data
public class ActivityResponse {
        private String id;
        private String userId;
        private ActivityType type;
        private Integer duration;
        private Integer caloriesBurned;
        private LocalDateTime startTime;
        private Map<String, Object> additionalMetrics;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
