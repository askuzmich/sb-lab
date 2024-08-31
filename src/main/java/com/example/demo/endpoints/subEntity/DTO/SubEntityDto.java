package com.example.demo.endpoints.subEntity.DTO;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;

public record SubEntityDto(
        String id,
        String name,
        String description,
        String imgUrl,
        HeadObjectDto owner
) {
}
