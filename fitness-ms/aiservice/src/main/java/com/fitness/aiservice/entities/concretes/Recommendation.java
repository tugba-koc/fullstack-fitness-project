package com.fitness.aiservice.entities.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "recommendations")
public class Recommendation {
        @Id
        private String id;
        
        private String userId;
        private String activityId;
        private String activityType;
        private String recommendation;
        private List<String> improvements;
        private List<String> suggestions;
        private List<String> safety;

        @CreatedDate
        private LocalDateTime createdAt;
}
