package com.fitness.userservice.business.abstracts;

import com.fitness.userservice.business.dto.requests.UserRegisterRequest;
import com.fitness.userservice.business.dto.responses.UserProfileResponse;
import com.fitness.userservice.business.dto.responses.UserResponse;

public interface UserService {
    UserProfileResponse getUserProfile(String userId);
    UserResponse register(UserRegisterRequest userRegisterRequest);
}
