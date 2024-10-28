package com.example.demo.endpoints.headObject.DTO;

import jakarta.validation.constraints.NotEmpty;

public record HeadObjectDto(
        Integer id,

        @NotEmpty(message = "name is required")
        String name,

        Integer hasSubEntities
) {
}
