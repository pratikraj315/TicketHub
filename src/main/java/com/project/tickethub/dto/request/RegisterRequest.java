package com.project.tickethub.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must contain at least 8 characters")
        String password

) {
}
//A normal Java class would require us to write:
//        - Private fields.
//        - Constructor.
//- Getters.
//- Possibly equals(), hashCode() and toString().
//A record generates these automatically.