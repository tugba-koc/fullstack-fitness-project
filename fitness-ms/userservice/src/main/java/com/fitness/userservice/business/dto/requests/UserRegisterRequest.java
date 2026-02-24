package com.fitness.userservice.business.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {
        @NotBlank(message = "Email is required")
        @NotNull(message = "Email must not be null")
        @NotEmpty(message = "Email must not be empty")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Password is required")
        @NotNull(message = "Password must not be null")
        @NotEmpty(message = "Password must not be empty")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        private String password;

        @NotBlank(message = "First name is required")
        @NotNull(message = "First name must not be null")
        @NotEmpty(message = "First name must not be empty")
        private String firstName;


        @NotBlank(message = "Last name is required")
        @NotNull(message = "Last name must not be null")
        @NotEmpty(message = "Last name must not be empty")
        private String lastName;
}
