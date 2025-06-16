package com.example.iWishTheyWereAllDead.repository;

import com.example.iWishTheyWereAllDead.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAll();
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}