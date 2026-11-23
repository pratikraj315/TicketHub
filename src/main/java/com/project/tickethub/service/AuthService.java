package com.project.tickethub.service;

import com.project.tickethub.dto.request.RegisterRequest;
import com.project.tickethub.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

}
//The service layer will handle:
//- Checking whether the email is already registered.
//- Hashing the user's password.
//- Assigning the default USER role.
//- Saving the new user in MySQL.


//Why an interface?
//The interface defines what the authentication service can do, without specifying how it does it.
//
//We'll put the actual registration logic in AuthServiceImpl.
//
//This separation makes it easier to:
//- Change implementation without changing the contract.
//- Mock the service in controller tests.
//- Keep responsibilities clear.
//We're using an interface here because our project blueprint follows this service-interface/implementation structure.