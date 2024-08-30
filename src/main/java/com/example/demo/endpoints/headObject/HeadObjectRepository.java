package com.example.demo.endpoints.headObject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadObjectRepository extends JpaRepository<HeadObject, String> {
}
