package com.fitness.userservice.business.concretes;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.userservice.business.abstracts.UserService;
import com.fitness.userservice.business.dto.requests.UserRegisterRequest;
import com.fitness.userservice.business.dto.responses.UserProfileResponse;
import com.fitness.userservice.business.dto.responses.UserResponse;
import com.fitness.userservice.dataAccess.abstracts.UserRepository;
import com.fitness.userservice.entities.concretes.User;

@Service
public class UserServiceManager implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserProfileResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return UserProfileResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @Override
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        boolean isEmailExists = userRepository.existsByEmail(userRegisterRequest.getEmail());

        if (isEmailExists) {
            throw new RuntimeException("User with email " + userRegisterRequest.getEmail() + " already exists.");
        }

        User createdUser = User.builder()
                .email(userRegisterRequest.getEmail())
                .password(userRegisterRequest.getPassword())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .build();

        userRepository.save(createdUser);

        return UserResponse.builder()
                .status("User registered successfully")
                .datetime(LocalDateTime.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @Override
    public Boolean existByUserId(String userId) {
        return userRepository.existsById(userId);
    }
}
