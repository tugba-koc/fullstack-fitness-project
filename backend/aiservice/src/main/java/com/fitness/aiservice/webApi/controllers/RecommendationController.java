package com.fitness.aiservice.webApi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.aiservice.business.abstracts.RecommendationService;
import com.fitness.aiservice.entities.concretes.Recommendation;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/recommendations")
@AllArgsConstructor
public class RecommendationController {
        private RecommendationService recommendationService;
        
        @GetMapping("/user/{userId}")
        public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {
            return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
        }
        
        @GetMapping("/activity/{activityId}")
        public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {
            return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
        }
}
