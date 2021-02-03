package com.example.demo.services;

import com.example.demo.models.entities.RoleEntity;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.ins.AuthRequest;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.models.outs.AuthResponse;
import com.example.demo.repositories.RoleEntityRepository;
import com.example.demo.repositories.UserEntityRepository;
import com.example.demo.securities.jwt.JwtProvider;
import com.example.demo.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProvider jwtProvider;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public ResponseEntity<UserEntity> registerUser(RegistrationRequest registrationRequest){
        UserEntity u = userMapper.convertToEntity(registrationRequest);
        u = saveUser(u);
        return ResponseEntity.ok(u);
    }

    public ResponseEntity<AuthResponse> auth(AuthRequest request){
        UserEntity userEntity = findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
