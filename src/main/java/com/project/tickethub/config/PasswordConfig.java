package com.project.tickethub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//Why BCrypt?
//
//We must never store plain-text passwords.
//
//BCrypt:
//- Hashes passwords using a one-way algorithm.
//- Automatically generates a salt.
//- Makes password guessing computationally expensive.
//Why PasswordEncoder instead of directly using BCryptPasswordEncoder everywhere?
//
//PasswordEncoder is an interface. This lets us change the implementation later without changing our service logic.
//
//@Bean registers the encoder in Spring's application context so Spring can inject it wherever required.