package com.fitness.activityservice.webApi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.activityservice.business.abstracts.ActivityService;
import com.fitness.activityservice.business.dto.requests.ActivityRequest;
import com.fitness.activityservice.business.dto.responses.ActivityResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivity(@RequestHeader("X-User-Id") String id) {
        return ResponseEntity.ok(activityService.getUserActivity(id));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId) {
        return ResponseEntity.ok(activityService.getActivity(activityId));
    }
    
}
