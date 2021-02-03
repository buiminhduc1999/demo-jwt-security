package com.example.demo.services.mappers;

import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.ins.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity convertToEntity(RegistrationRequest registrationRequest){
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        return u;
    }
}
