package com.fitness.activityservice.business.concretes;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fitness.activityservice.business.abstracts.ActivityService;
import com.fitness.activityservice.business.dto.requests.ActivityRequest;
import com.fitness.activityservice.business.dto.responses.ActivityResponse;
import com.fitness.activityservice.business.mappers.ActivityMapper;
import com.fitness.activityservice.dataAccess.abstracts.ActivityRepository;
import com.fitness.activityservice.entities.concretes.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class ActivityServiceManager implements ActivityService {
    private final ActivityRepository activityRepository;
    private final UserValidateServiceManager userValidateService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    
    @Override
    public ActivityResponse trackActivity(ActivityRequest request) {
        // Validate user existence
        boolean isValidUser = userValidateService.validateUserId(request.getUserId());
        
        if (!isValidUser) {
                throw new RuntimeException("User with id " + request.getUserId() + " not found.");
        }

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        // Save the activity to the database
        Activity savedActivity = activityRepository.save(activity);

        // Publish event to RabbitMQ for AI recommendation
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
        } catch (Exception e) {
            log.error("Failed to publish activity event to RabbitMQ", e);
        }
        
        return ActivityMapper.toActivityResponse(savedActivity);
    }

    @Override
    public List<ActivityResponse> getUserActivity(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(ActivityMapper::toActivityResponse)
                .toList();
    }

    @Override
    public ActivityResponse getActivity(String activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));

        return ActivityMapper.toActivityResponse(activity);
    }
}
