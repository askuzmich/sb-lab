package com.example.demo.endpoints.headObject.converter;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
import com.example.demo.endpoints.headObject.HeadObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToHeadObject implements Converter<HeadObjectDto, HeadObject> {
    @Override
    public HeadObject convert(HeadObjectDto source) {
        HeadObject headObject = new HeadObject();
        headObject.setId(source.id());
        headObject.setName(source.name());

        return headObject;
    }
}
