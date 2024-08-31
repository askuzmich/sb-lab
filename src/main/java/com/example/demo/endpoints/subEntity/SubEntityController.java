package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
import com.example.demo.endpoints.subEntity.converter.SubEntityToDTO;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubEntityController {
    private final SubEntityService subEntityService;

    private final SubEntityToDTO subEntityToDTO;

    public SubEntityController(SubEntityService subEntityService, SubEntityToDTO subEntityToDTO) {
        this.subEntityService = subEntityService;
        this.subEntityToDTO = subEntityToDTO;
    }

    @GetMapping("/api/v1/subEntities/{id}")
    public CustomReturnData findSubEntityById(@PathVariable String id) {
        SubEntity subEntity = this.subEntityService.findById(id);

        SubEntityDto subEntityDto = this.subEntityToDTO.convert(subEntity);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                subEntityDto
        );
    }
}
