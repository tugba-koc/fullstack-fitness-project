package com.fitness.aiservice.dataAccess.abstracts;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fitness.aiservice.entities.concretes.Recommendation;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
        
}
