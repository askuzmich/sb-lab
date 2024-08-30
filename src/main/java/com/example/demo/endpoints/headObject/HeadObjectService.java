package com.example.demo.endpoints.headObject;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HeadObjectService {
    private final HeadObjectRepository headObjectRepository;

    public HeadObjectService(HeadObjectRepository headObjectRepository) {
        this.headObjectRepository = headObjectRepository;
    }

    public HeadObject findById(String id) {
        return null;
    }
}
