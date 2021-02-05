package com.example.demo.securities.webconfig;

import com.example.demo.models.entities.RoleEntity;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.services.process.impls.RoleService;
import com.example.demo.services.process.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService iUserService;

    @Autowired
    private RoleService iRoleService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = iUserService.getByUserName(username);
        RoleEntity roleEntity = iRoleService.findById(userEntity.getIdRole());
        return new CustomUserDetails(userEntity.getUserName(), userEntity.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(roleEntity.getName())));
    }
}
