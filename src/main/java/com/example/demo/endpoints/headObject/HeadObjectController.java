package com.example.demo.endpoints.headObject;

import com.example.demo.endpoints.headObject.DTO.HeadObjectDto;
import com.example.demo.endpoints.headObject.converter.DtoToHeadObject;
import com.example.demo.endpoints.headObject.converter.HeadObjectToDTO;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/headObjects")
public class HeadObjectController {

    private final HeadObjectService headObjectService;

    private final HeadObjectToDTO headObjectToDTO;

    private final DtoToHeadObject dtoToHeadObject;

    public HeadObjectController(
            HeadObjectService headObjectService,
            HeadObjectToDTO headObjectToDTO,
            DtoToHeadObject dtoToHeadObject
    ) {
        this.headObjectService = headObjectService;
        this.headObjectToDTO = headObjectToDTO;
        this.dtoToHeadObject = dtoToHeadObject;
    }

    @GetMapping("/{id}")
    public CustomReturnData findHeadObjectById(@PathVariable Integer id) {
        HeadObject headObject = this.headObjectService.findById(id);

        HeadObjectDto headObjectDto = this.headObjectToDTO.convert(headObject);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                headObjectDto
        );
    }

    @GetMapping
    public CustomReturnData getAll() {
        List<HeadObject> headObjects = headObjectService.getAll();

        List<HeadObjectDto> headObjectDTOs = headObjects
                .stream()
                .map((headObject -> {
                    return this.headObjectToDTO.convert(headObject);
                }))
                .collect(Collectors.toList());

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                headObjectDTOs
        );
    }

    @PostMapping
    public CustomReturnData add(@Valid @RequestBody HeadObjectDto dto) {
        HeadObject convert = this.dtoToHeadObject.convert(dto);

        HeadObject add = this.headObjectService.add(convert);

        HeadObjectDto newDto = this.headObjectToDTO.convert(add);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                newDto
        );
    }

    @PutMapping("/{id}")
    public CustomReturnData update(
        @PathVariable Integer id,
        @Valid @RequestBody HeadObjectDto dto
    ) {
        HeadObject convertedHO = this.dtoToHeadObject.convert(dto);

        HeadObject updated = this.headObjectService.update(id, convertedHO);

        HeadObjectDto retDto = this.headObjectToDTO.convert(updated);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                retDto
        );
    }

    @DeleteMapping("/{id}")
    public CustomReturnData delete(@PathVariable Integer id) {
        this.headObjectService.delete(id);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Transaction is Ok"
        );
    }

    @PutMapping("/{hid}/subEntities/{sid}")
    public CustomReturnData assignSubEntity(@PathVariable Integer hid, @PathVariable String sid) {
        this.headObjectService.assignmentSubEntity(hid, sid);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Assignment is Ok"
        );
    }
}
