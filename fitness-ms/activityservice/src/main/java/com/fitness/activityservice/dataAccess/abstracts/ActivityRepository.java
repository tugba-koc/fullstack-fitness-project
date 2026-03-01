package com.fitness.activityservice.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitness.activityservice.entities.concretes.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
       List<Activity> findByUserId(String userId);
}
