package com.project.tickethub.service.impl;

import com.project.tickethub.dto.request.RegisterRequest;
import com.project.tickethub.dto.response.AuthResponse;
import org.springframework.dao.DataIntegrityViolationException;
import com.project.tickethub.entity.User;
import com.project.tickethub.enums.Role;
import com.project.tickethub.exception.DuplicateEmailException;
import com.project.tickethub.repository.UserRepository;
import com.project.tickethub.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(
                    "Email is already registered"
            );
        }

        User user = new User(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.USER
        );

        User savedUser;

        try {
            savedUser = userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEmailException(
                    "Email is already registered"
            );
        }
        /*saveAndFlush() forces Hibernate to send the insert to the database immediately, allowing us to catch the unique-constraint violation here rather than waiting until transaction commit.
For this registration operation, the relevant database integrity conflict is the unique email constraint. The database remains the final protection against duplicate registrations. */

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}



//Code	Why we're using it
//@Service	Registers this class as a Spring service bean
//@RequiredArgsConstructor	Lombok generates a constructor for our final dependencies
//private final	Makes dependencies constructor-injected and immutable
//@Transactional	Keeps registration database operations within a transaction
//existsByEmail()	Checks for an existing account
//passwordEncoder.encode()	Hashes the password before saving
//Role.USER	Prevents users from choosing their own role
//userRepository.save()	Persists the new user
//AuthResponse	Returns safe user information without exposing the password hash
//
//One important database detail: The email column also has a unique constraint. The existsByEmail() check improves the normal user experience, but two simultaneous registrations could pass that check. The database constraint is the final protection. We'll handle that race condition properly as part of exception handling.