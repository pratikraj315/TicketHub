package com.project.tickethub.controller;

import com.project.tickethub.dto.request.RegisterRequest;
import com.project.tickethub.dto.response.AuthResponse;
import com.project.tickethub.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

//Why ResponseEntity?
//It lets us control the HTTP response status and response body.

//Annotation	Purpose
//@RestController	Makes this class a REST API controller
//@RequestMapping("/api/auth")	Sets the common URL prefix
//@RequiredArgsConstructor	Generates constructor injection for AuthService
//@PostMapping("/register")	Maps HTTP POST requests to registration
//@RequestBody	Converts incoming JSON into RegisterRequest
//@Valid	Triggers the DTO's validation annotations
