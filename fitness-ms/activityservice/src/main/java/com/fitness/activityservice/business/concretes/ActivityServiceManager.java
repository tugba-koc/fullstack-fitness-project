package com.fitness.activityservice.business.concretes;

import org.springframework.stereotype.Service;

import com.fitness.activityservice.business.abstracts.ActivityService;
import com.fitness.activityservice.business.dto.requests.ActivityRequest;
import com.fitness.activityservice.business.dto.responses.ActivityResponse;

@Service
public class ActivityServiceManager implements ActivityService {
    @Override
    public ActivityResponse trackActivity(ActivityRequest request) {
        return null;
    }
}
