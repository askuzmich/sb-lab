package com.example.demo.endpoints.subEntity.DTO;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
import jakarta.validation.constraints.NotEmpty;

public record SubEntityDto(
        String id,
        @NotEmpty(message = "name is required")
        String name,
        @NotEmpty(message = "description is required")
        String description,
        @NotEmpty(message = "imgUrl is required")
        String imgUrl,
        HeadObjectDto owner
) {
}
