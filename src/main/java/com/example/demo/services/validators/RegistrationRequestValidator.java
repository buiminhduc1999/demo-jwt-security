package com.example.demo.services.validators;

import com.example.demo.exceptions.Response;
import com.example.demo.exceptions.ResponseDetail;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestValidator {

    @Autowired
    private UserRepository userRepository;

    public <T> ResponseEntity<ResponseDetail<T>> validateRegistration(RegistrationRequest registrationRequest) {

        if (registrationRequest.getUserName() == null || registrationRequest.getUserName().isEmpty())
            return Response.badRequest(MessageResponse.USERNAME_IS_NULL);

        if (registrationRequest.getPassword() == null || registrationRequest.getPassword().isEmpty())
            return Response.badRequest(MessageResponse.PASSWORD_IS_NULL);

        if (userRepository.existsByUserName(registrationRequest.getUserName())) {
            return Response.badRequest(MessageResponse.USERNAME_IS_ALREADY_TAKEN);
        }

        return Response.ok();
    }

}
