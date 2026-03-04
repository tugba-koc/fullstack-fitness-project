package com.fitness.aiservice.business.abstracts;

import java.util.List;

import com.fitness.aiservice.entities.concretes.Recommendation;

public interface RecommendationService {
        List<Recommendation> getUserRecommendation(String userId);
        List<Recommendation> getActivityRecommendation(String activityId);
}
