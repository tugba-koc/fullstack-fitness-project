package com.fitness.activityservice.dataAccess.abstracts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fitness.activityservice.entities.concretes.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
       boolean existsByUserId(String userId);
}
