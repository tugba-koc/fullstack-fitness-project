package com.fitness.aiservice.business.listener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fitness.aiservice.business.concretes.ActivityAiManager;
import com.fitness.aiservice.dataAccess.abstracts.RecommendationRepository;
import com.fitness.aiservice.entities.concretes.Activity;
import com.fitness.aiservice.entities.concretes.Recommendation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {
        private final ActivityAiManager activityAiManager;
        private final RecommendationRepository recommendationRepository;

        @RabbitListener(queues = "activity.queue")
        public void receiveActivityMessage(Activity activity) {
                log.info("Received activity message: {}", activity);
                Recommendation recommendation = activityAiManager.generateRecommendation(activity);
                recommendationRepository.save(recommendation);
        }
}