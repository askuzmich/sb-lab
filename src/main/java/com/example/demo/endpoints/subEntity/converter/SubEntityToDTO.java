package com.example.demo.endpoints.subEntity.converter;

import com.example.demo.endpoints.headObject.converter.HeadObjectToDTO;
import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
import com.example.demo.endpoints.subEntity.SubEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubEntityToDTO implements Converter<SubEntity, SubEntityDto> {
    private final HeadObjectToDTO headObjectToDTO;

    public SubEntityToDTO(HeadObjectToDTO headObjectToDTO) {
        this.headObjectToDTO = headObjectToDTO;
    }

    @Override
    public SubEntityDto convert(SubEntity source) {
        SubEntityDto subEntityDto = new SubEntityDto(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getImgUrl(),
                source.getOwner() != null
                    ? this.headObjectToDTO.convert(source.getOwner())
                    : null
        );

        return subEntityDto;
    }
}
