package com.fitness.userservice.entities.concretes;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fitness.userservice.entities.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name="id")
        private String id;

        @Column(name="email", nullable = false, unique = true)
        private String email;

        @Column(name="first_name", nullable = false)
        private String firstName;

        @Column(name="last_name", nullable = false)
        private String lastName;

        @Column(name="password", nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        private UserRole role = UserRole.USER;

        @CurrentTimestamp
        @Column(name="created_at", nullable = false)
        private LocalDateTime createdAt;

        @UpdateTimestamp
        @Column(name="updated_at")
        private LocalDateTime updatedAt;
}
