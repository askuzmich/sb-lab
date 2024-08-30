package com.example.demo.endpoints.subEntity;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SubEntityService {
    private final SubEntityRepository subEntityRepository;

    public SubEntityService(SubEntityRepository subEntityRepository) {
        this.subEntityRepository = subEntityRepository;
    }

    public SubEntity findById(String id) {
        return this.subEntityRepository
                .findById(id)
                .orElseThrow(() -> new SubEntityNotFoundException(id));
    }
}
