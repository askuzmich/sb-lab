package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<SubEntity> getAll() {
        return this.subEntityRepository.findAll();
    }
}
