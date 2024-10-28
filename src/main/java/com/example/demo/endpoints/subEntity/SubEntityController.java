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
@RequestMapping("${api.endpoint.base-url}/subEntities")
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

    @GetMapping("/{id}")
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

    @GetMapping
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

    @PostMapping
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

    @PutMapping("/{id}")
    public CustomReturnData update(
        @PathVariable String id,
        @Valid @RequestBody SubEntityDto dto
    ) {
        SubEntity convertedEntity = this.dtoToSubEntity.convert(dto);

        SubEntity updated = this.subEntityService.update(id, convertedEntity);

        SubEntityDto retDto = this.subEntityToDTO.convert(updated);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Transaction is Ok",
            retDto
        );
    }

    @DeleteMapping("/{id}")
    public CustomReturnData delete(@PathVariable String id) {
        this.subEntityService.delete(id);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Transaction is Ok"
        );
    }
}
