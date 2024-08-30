package com.example.demo.endpoints.headObject;

import com.example.demo.returnDataObject.CustomReturnData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadObjectController {

    private final HeadObjectService headObjectService;

    public HeadObjectController(HeadObjectService headObjectService) {
        this.headObjectService = headObjectService;
    }

    @GetMapping("/api/v1/headObjects/{id}")
    public CustomReturnData findHeadObjectById(@PathVariable String id) {
        return null;
    }
}
