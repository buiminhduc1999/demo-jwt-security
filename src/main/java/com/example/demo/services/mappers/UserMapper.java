package com.example.demo.services.mappers;

import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.models.outs.UserResponse;
import com.example.demo.models.outs.UserResponseOfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity convertToEntity(RegistrationRequest registrationRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setIdRole(1);
        userEntity.setStatus(true);
        userEntity.setUserName(registrationRequest.getUserName());
        userEntity.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return userEntity;
    }

    public UserResponse convertToDto (UserEntity userEntity){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setIdRole(userEntity.getIdRole());
        userResponse.setUserName(userEntity.getUserName());
        userResponse.setPassword(userEntity.getPassword());
        userResponse.setStatus(userEntity.isStatus());
        return userResponse;
    }

    public List<UserResponse> convertListEntityToDto(List<UserEntity> studentEntities){
        return  studentEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserResponseOfUser convertToResponseOfUser(UserEntity userEntity){
        UserResponseOfUser userResponseOfUser = new UserResponseOfUser();
        userResponseOfUser.setUserName(userEntity.getUserName());
        return userResponseOfUser;
    }
}
