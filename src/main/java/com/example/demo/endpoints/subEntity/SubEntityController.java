package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.subEntity.DTO.SubEntityDto;
import com.example.demo.endpoints.subEntity.converter.DtoToSubEntity;
import com.example.demo.endpoints.subEntity.converter.SubEntityToDTO;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SubEntityController {
    private final SubEntityService subEntityService;

    private final SubEntityToDTO subEntityToDTO;

    private final DtoToSubEntity dtoToSubEntity;


    public SubEntityController(
            SubEntityService subEntityService,
            SubEntityToDTO subEntityToDTO,
            DtoToSubEntity dtoToSubEntity
    ) {
        this.subEntityService = subEntityService;
        this.subEntityToDTO = subEntityToDTO;
        this.dtoToSubEntity = dtoToSubEntity;
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

    @GetMapping("/api/v1/subEntities")
    public CustomReturnData getAll() {
        List<SubEntity> subEntities = subEntityService.getAll();

        List<SubEntityDto> subEntityToDTOS = subEntities
                .stream()
                .map((subEntity) -> {
                    return this.subEntityToDTO.convert(subEntity);
                })
                .collect(Collectors.toList());

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                subEntityToDTOS
        );
    }

    @PostMapping("/api/v1/subEntities")
    public CustomReturnData add(@Valid @RequestBody SubEntityDto subEntityDto) {
        SubEntity convert = this.dtoToSubEntity.convert(subEntityDto);

        SubEntity add = this.subEntityService.add(convert);

        SubEntityDto convertedDto = this.subEntityToDTO.convert(add);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                convertedDto
        );
    }
}
