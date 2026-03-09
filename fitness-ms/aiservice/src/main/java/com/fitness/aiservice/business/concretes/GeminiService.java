package com.fitness.aiservice.business.concretes;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Value;

@Service
public class GeminiService {
        private final WebClient webClient;

        @Value("${gemini.api.key}")
        private String geminiApiKey;

        @Value("${gemini.api.url}")
        private String geminiApiUrl;

        public GeminiService(WebClient.Builder webClientBuilder) {
                this.webClient = webClientBuilder.build();
        }

        public String generateRecommendation(String prompt) {
                Map<String, Object> requestBody = Map.of(
                        "contents", new Object[] {
                                Map.of(
                                        "parts", new Object[] {
                                                Map.of(
                                                        "text", prompt
                                                )
                                        })
                });

                String response = webClient.post()
                        .uri(geminiApiUrl)
                        .header("Content-Type", "application/json")
                        .header("X-goog-api-key", geminiApiKey)
                        .bodyValue(requestBody)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                return response;
        }
}
