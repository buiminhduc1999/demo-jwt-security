package com.example.demo.controllers;

import com.example.demo.securities.jwt.JwtProvider;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.ins.AuthRequest;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.models.outs.AuthResponse;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        return userService.auth(request);
    }
}
