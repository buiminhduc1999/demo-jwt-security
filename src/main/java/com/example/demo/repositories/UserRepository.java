package com.example.demo.repositories;

import com.example.demo.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserName(String userName);

    Boolean existsByUserName(String username);
}
