package com.example.demo.endpoints.headObject.converter;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
import com.example.demo.endpoints.headObject.HeadObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HeadObjectToDTO implements Converter<HeadObject, HeadObjectDto> {
    @Override
    public HeadObjectDto convert(HeadObject source) {
        HeadObjectDto headObjectDto = new HeadObjectDto(
                source.getId(),
                source.getName(),
                source.getNumberOfSE()
        );

        return headObjectDto;
    }
}
