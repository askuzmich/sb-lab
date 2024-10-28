package com.example.demo.endpoints.headObject;

//import com.example.demo.endpoints.headObject.exc.HeadObjectNotFoundException;
import com.example.demo.endpoints.subEntity.SubEntity;
import com.example.demo.endpoints.subEntity.SubEntityRepository;
import com.example.demo.exception.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HeadObjectService {
    private final HeadObjectRepository headObjectRepository;

    private final SubEntityRepository subEntityRepository;

    public HeadObjectService(HeadObjectRepository headObjectRepository, SubEntityRepository subEntityRepository) {
        this.headObjectRepository = headObjectRepository;
        this.subEntityRepository = subEntityRepository;
    }

    public HeadObject findById(Integer id) {
        return this.headObjectRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Head Object", id));
    }

    public List<HeadObject> getAll() {
        return this.headObjectRepository.findAll();
    }

    public HeadObject add(HeadObject headObject) {
        return this.headObjectRepository.save(headObject);
    }

    public HeadObject update(Integer id, HeadObject candidate) {
        return headObjectRepository
                .findById(id)
                .map((existHO) -> {
                    existHO.setName(candidate.getName());
//                    existHO.setSubEntities(candidate.getSubEntities());

                    return headObjectRepository.save(existHO);
                }).orElseThrow(() -> new CustomNotFoundException("Head Object", id));
    }

    public void delete(Integer id) {
        HeadObject headObject = this.headObjectRepository.findById(id).orElseThrow(() -> {
            return new CustomNotFoundException("Head Object", id);
        });

        headObject.removeAllSubEntities();

        this.headObjectRepository.deleteById(id);
    }

    public void assignmentSubEntity(Integer hid, String sid) {
        SubEntity subEntity = this.subEntityRepository
                .findById(sid)
                .orElseThrow(() -> {
                    return new CustomNotFoundException("SubEntity", sid);
                });

        HeadObject headObject = this.headObjectRepository
                .findById(hid)
                .orElseThrow(() -> {
                    return new CustomNotFoundException("Head Object", hid);
                });

        if (subEntity.getOwner() != null) {
            subEntity.getOwner().removeSubEntity(subEntity);
        }

        headObject.addSubEntity(subEntity);
    }
}
