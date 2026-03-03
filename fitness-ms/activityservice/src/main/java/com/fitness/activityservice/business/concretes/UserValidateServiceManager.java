package com.fitness.activityservice.business.concretes;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fitness.activityservice.business.abstracts.UserValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserValidateServiceManager implements UserValidateService {
        private final WebClient userServiceWebClient;

        @Override
        public boolean validateUserId(String userId) {
                try {
                        return userServiceWebClient.get()
                        .uri("/api/users/{userId}/validate", userId)
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .block();
                } catch (WebClientResponseException e) {
                        if(e.getStatusCode().is4xxClientError()) {
                                throw new RuntimeException("User with id " + userId + " not found.");
                        }
                        throw e;
                }
        }
        
}
