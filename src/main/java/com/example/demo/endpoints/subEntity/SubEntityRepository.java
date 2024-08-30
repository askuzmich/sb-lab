package com.example.demo.endpoints.subEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubEntityRepository extends JpaRepository<SubEntity, String> {
}
