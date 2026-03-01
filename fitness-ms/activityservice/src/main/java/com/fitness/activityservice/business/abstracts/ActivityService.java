package com.fitness.activityservice.business.abstracts;

import com.fitness.activityservice.business.dto.requests.ActivityRequest;
import com.fitness.activityservice.business.dto.responses.ActivityResponse;

public interface ActivityService {
        ActivityResponse trackActivity(ActivityRequest activityRequest);
}
