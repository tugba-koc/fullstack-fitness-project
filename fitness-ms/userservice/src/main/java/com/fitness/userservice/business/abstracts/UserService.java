package com.fitness.userservice.business.abstracts;

import com.fitness.userservice.business.dto.responses.UserResponse;

public interface UserService {
    UserResponse getUserProfile(String userId);
    UserResponse register(String bearerToken, UpdateUserEmailAddressRequest updateUserEmailAddressRequest);
}
