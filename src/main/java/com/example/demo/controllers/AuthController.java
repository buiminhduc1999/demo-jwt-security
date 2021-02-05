package com.example.demo.controllers;

import com.example.demo.exceptions.ResponseDetail;
import com.example.demo.models.ins.AuthRequest;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.models.outs.AuthResponse;
import com.example.demo.models.outs.UserResponse;
import com.example.demo.services.process.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService iUserService;

    @PostMapping("/registers")
    public ResponseEntity<ResponseDetail<UserResponse>> register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return iUserService.register(registrationRequest);
    }

    @PostMapping("/auths")
    public ResponseEntity<ResponseDetail<AuthResponse>> auth(@RequestBody AuthRequest request) {
        return iUserService.auth(request);
    }

}
