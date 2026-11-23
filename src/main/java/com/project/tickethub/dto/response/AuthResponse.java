package com.project.tickethub.dto.response;

import com.project.tickethub.enums.Role;

public record AuthResponse(
        Long id,
        String name,
        String email,
        Role role
) {
}
//Because our service should return application data suitable for the API, not expose our database entity.