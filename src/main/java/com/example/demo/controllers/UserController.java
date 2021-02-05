package com.example.demo.controllers;

import com.example.demo.exceptions.ResponseDetail;
import com.example.demo.models.outs.UserResponse;
import com.example.demo.models.outs.UserResponseOfUser;
import com.example.demo.services.process.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDetail<List<UserResponse>>> getAll() {
        return userService.getAll();
    }

    @GetMapping("/users/{userName}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDetail<UserResponseOfUser>> getUserInfo(HttpServletRequest request, @PathVariable("userName") String userName) {
        return userService.getUserById(request, userName);
    }

}
