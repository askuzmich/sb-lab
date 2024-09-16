package com.example.demo.endpoints.subEntity.converter;

import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
import com.example.demo.endpoints.subEntity.SubEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToSubEntity implements Converter<SubEntityDto, SubEntity> {
    @Override
    public SubEntity convert(SubEntityDto source) {
        SubEntity subEntity = new SubEntity();
        subEntity.setId(source.id());
        subEntity.setName(source.name());
        subEntity.setDescription(source.description());
        subEntity.setImgUrl(source.imgUrl());

        return subEntity;
    }
}
