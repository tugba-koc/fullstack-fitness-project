package com.fitness.aiservice.business.listener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fitness.aiservice.entities.concretes.Activity;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ActivityMessageListener {
        @RabbitListener(queues = "activity.queue")
        public void receiveActivityMessage(Activity activity) {
                log.info("Received activity message: {}", activity);
        }
}