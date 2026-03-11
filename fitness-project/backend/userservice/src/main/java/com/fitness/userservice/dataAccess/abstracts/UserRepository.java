package com.fitness.userservice.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.userservice.entities.concretes.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
       boolean existsByEmail(String email);
       Optional<User> findById(String id);
//     Optional<User> findByEmail(String email);

}
