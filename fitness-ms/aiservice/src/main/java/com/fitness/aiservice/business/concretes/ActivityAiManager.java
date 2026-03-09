package com.fitness.aiservice.business.concretes;

import org.springframework.stereotype.Service;

import com.fitness.aiservice.entities.concretes.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAiManager {
        private final GeminiManager geminiManager;

        public String generateRecommendation(Activity activity) {
                String prompt = createPromptForActivity(activity);
                String aiResponse = geminiManager.getAnswer(prompt);
                log.info("response from ai: {} ", aiResponse);
                return aiResponse; 
        }


        private String createPromptForActivity(Activity activity) {
                return String.format(
                        "I am doing %s for %d minutes. Can you suggest a suitable warm-up exercise and a cool-down exercise for this activity?",
                        activity.getType(), activity.getDuration());
        }
}
