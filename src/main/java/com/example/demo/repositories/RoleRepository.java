package com.example.demo.repositories;

import com.example.demo.models.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity getRoleEntityById(int id);

}
