package com.example.demo.endpoints.sbUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SbUser, Integer> {
  // Derived Query Methods. The same as "SELECT * FROM SB_USER WHERE NAME=?
  // Optional<SbUser> findByNameAndEnabled(String userName, Boolean isEnabled)
  Optional<SbUser> findByName(String name);
}
