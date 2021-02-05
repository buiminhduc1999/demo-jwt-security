package com.example.demo.services.validators;

import com.example.demo.exceptions.Response;
import com.example.demo.exceptions.ResponseDetail;
import com.example.demo.models.ins.AuthRequest;
import com.example.demo.utils.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestValidator {

    public <T> ResponseEntity<ResponseDetail<T>> validateAuth(AuthRequest authRequest) {

        if (authRequest.getUserName() == null || authRequest.getUserName().isEmpty())
            return Response.badRequest(MessageResponse.USERNAME_IS_NULL);

        if (authRequest.getPassword() == null || authRequest.getPassword().isEmpty())
            return Response.badRequest(MessageResponse.PASSWORD_IS_NULL);

        return Response.ok();

    }


}
