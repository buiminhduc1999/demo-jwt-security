package com.example.demo.services.process;

import com.example.demo.models.entities.RoleEntity;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.process.impls.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findById(int id) {
        return roleRepository.getRoleEntityById(id);
    }

}
