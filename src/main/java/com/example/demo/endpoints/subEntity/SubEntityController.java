package com.example.demo.endpoints.subEntity;

import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubEntityController {
    private final SubEntityService subEntityService;

    public SubEntityController(SubEntityService subEntityService) {
        this.subEntityService = subEntityService;
    }

    @GetMapping("/api/v1/subEntities/{id}")
    public CustomReturnData findSubEntityById(@PathVariable String id) {
        SubEntity data = this.subEntityService.findById(id);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                data
        );
    }
}
