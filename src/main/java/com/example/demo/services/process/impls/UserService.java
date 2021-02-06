package com.example.demo.services.process.impls;

import com.example.demo.exceptions.ResponseDetail;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.ins.AuthRequest;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.models.ins.TokenRequest;
import com.example.demo.models.outs.AuthResponse;
import com.example.demo.models.outs.UserResponse;
import com.example.demo.models.outs.UserResponseOfUser;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    ResponseEntity<ResponseDetail<List<UserResponse>>> getAll();

    ResponseEntity<ResponseDetail<UserResponse>> register(RegistrationRequest registrationRequest);

    ResponseEntity<ResponseDetail<AuthResponse>> auth(AuthRequest request);

    UserEntity getByUserName(String userName);

    UserEntity getByUserNameAndPassword(String userName, String password);

    ResponseEntity<ResponseDetail<UserResponseOfUser>> getUserById(HttpServletRequest request, String userName);
}
