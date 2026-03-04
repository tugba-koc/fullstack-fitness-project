package com.fitness.aiservice.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fitness.aiservice.business.abstracts.RecommendationService;
import com.fitness.aiservice.dataAccess.abstracts.RecommendationRepository;
import com.fitness.aiservice.entities.concretes.Recommendation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationManager implements RecommendationService {
        private final RecommendationRepository recommendationRepository;
        
        @Override
        public List<Recommendation> getUserRecommendation(String userId) {
        }
        
}
