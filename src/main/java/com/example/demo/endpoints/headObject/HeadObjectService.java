package com.example.demo.endpoints.headObject;

//import com.example.demo.endpoints.headObject.exc.HeadObjectNotFoundException;
import com.example.demo.exception.CustomNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HeadObjectService {
    private final HeadObjectRepository headObjectRepository;

    public HeadObjectService(HeadObjectRepository headObjectRepository) {
        this.headObjectRepository = headObjectRepository;
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
}
