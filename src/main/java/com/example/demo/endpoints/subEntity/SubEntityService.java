package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.subEntity.exception.SubEntityNotFoundException;
import com.example.demo.utis.UUID;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SubEntityService {
    private final SubEntityRepository subEntityRepository;

    private final UUID uuid;

    public SubEntityService(SubEntityRepository subEntityRepository, UUID uuid) {
        this.subEntityRepository = subEntityRepository;
        this.uuid = uuid;
    }

    public SubEntity findById(String id) {
        return this.subEntityRepository
                .findById(id)
                .orElseThrow(() -> new SubEntityNotFoundException(id));
    }

    public List<SubEntity> getAll() {
        return this.subEntityRepository.findAll();
    }

    public SubEntity add(SubEntity subEntity) {
        subEntity.setId(this.uuid.getId());

        return this.subEntityRepository.save(subEntity);
    }
}
