package com.fitness.activityservice.business.concretes;

import org.springframework.stereotype.Service;

import com.fitness.activityservice.business.abstracts.ActivityService;
import com.fitness.activityservice.business.dto.requests.ActivityRequest;
import com.fitness.activityservice.business.dto.responses.ActivityResponse;
import com.fitness.activityservice.business.mappers.ActivityMapper;
import com.fitness.activityservice.dataAccess.abstracts.ActivityRepository;
import com.fitness.activityservice.entities.concretes.Activity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceManager implements ActivityService {
    private final ActivityRepository activityRepository;
    
    @Override
    public ActivityResponse trackActivity(ActivityRequest request) {
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        // Save the activity to the database
        activityRepository.save(activity);
        
        return ActivityMapper.toActivityResponse(activity);
    }
}
