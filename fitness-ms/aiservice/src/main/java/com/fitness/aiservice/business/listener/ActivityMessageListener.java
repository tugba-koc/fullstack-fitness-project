package com.fitness.aiservice.business.listener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fitness.aiservice.business.concretes.ActivityAiManager;
import com.fitness.aiservice.entities.concretes.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {
        private final ActivityAiManager activityAiManager;

        @RabbitListener(queues = "activity.queue")
        public void receiveActivityMessage(Activity activity) {
                log.info("Received activity message: {}", activity);
                log.info("Generating recommendation for activity {}", activityAiManager.generateRecommendation(activity));
        }
}