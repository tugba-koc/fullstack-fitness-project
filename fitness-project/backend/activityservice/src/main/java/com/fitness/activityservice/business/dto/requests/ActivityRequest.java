package com.fitness.activityservice.business.dto.requests;

import java.time.LocalDateTime;
import java.util.Map;

import com.fitness.activityservice.entities.enums.ActivityType;

import lombok.Data;

@Data
public class ActivityRequest {
        private String userId;
        private ActivityType type;
        private Integer duration;
        private Integer caloriesBurned;
        private LocalDateTime startTime;
        private Map<String, Object> additionalMetrics;
}
