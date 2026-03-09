package com.fitness.aiservice.business.concretes;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAiManager {
        private final GeminiManager geminiManager;
}
