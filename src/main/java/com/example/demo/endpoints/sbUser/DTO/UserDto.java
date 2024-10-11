package com.example.demo.endpoints.sbUser.DTO;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        Integer id,

        @NotEmpty(message = "name is required")
        String name,

        @NotEmpty(message = "space separated roles are required")
        String roles,

        boolean enabled
) {
}
