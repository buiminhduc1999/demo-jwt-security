package com.example.demo.services.process;

import com.example.demo.exceptions.Response;
import com.example.demo.exceptions.ResponseDetail;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.ins.AuthRequest;
import com.example.demo.models.ins.RegistrationRequest;
import com.example.demo.models.ins.TokenRequest;
import com.example.demo.models.outs.AuthResponse;
import com.example.demo.models.outs.UserResponse;
import com.example.demo.models.outs.UserResponseOfUser;
import com.example.demo.repositories.UserRepository;
import com.example.demo.securities.jwt.JwtProvider;
import com.example.demo.services.mappers.UserMapper;
import com.example.demo.services.process.impls.TeamService;
import com.example.demo.services.process.impls.UserService;
import com.example.demo.services.validators.AuthRequestValidator;
import com.example.demo.services.validators.RegistrationRequestValidator;
import com.example.demo.utils.MessageResponse;
import com.example.demo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.*;

import static org.springframework.util.StringUtils.hasText;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthRequestValidator authRequestValidator;
    @Autowired
    private RegistrationRequestValidator registrationRequestValidator;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public ResponseEntity<ResponseDetail<List<UserResponse>>> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponse> userResponseList = userMapper.convertListEntityToDto(userEntities);
        return Response.ok(userResponseList);
    }

    @Override
    public ResponseEntity<ResponseDetail<UserResponse>> register(RegistrationRequest registrationRequest) {
        ResponseEntity<ResponseDetail<UserResponse>> validate = registrationRequestValidator.validateRegistration(registrationRequest);
        if (!validate.getStatusCode().is2xxSuccessful())
            return validate;
        UserEntity userEntity = userMapper.convertToEntity(registrationRequest);
        userEntity = userRepository.save(userEntity);
        UserResponse userResponse = userMapper.convertToDto(userEntity);
        return Response.ok(userResponse);
    }

    @Override
    public ResponseEntity<ResponseDetail<AuthResponse>> auth(AuthRequest request) {
        ResponseEntity<ResponseDetail<AuthResponse>> validate = authRequestValidator.validateAuth(request);
        if (!validate.getStatusCode().is2xxSuccessful())
            return validate;
        UserEntity userEntity = getByUserNameAndPassword(request.getUserName(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUserName());
        AuthResponse authResponse = new AuthResponse(token);
        return Response.ok(authResponse);
    }

    @Override
    public ResponseEntity<ResponseDetail<UserResponseOfUser>> getUserById(HttpServletRequest request, String userName) {
        UserEntity userEntity = getByUserName(userName);
        UserResponseOfUser users = userMapper.convertToResponseOfUser(userEntity);
        if (checkLeaderAndMember(request, userEntity, userName))
            return Response.ok(users);
        return Response.badRequest(MessageResponse.BAD_REQUEST);
    }

    @Override
    public UserEntity getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserEntity getByUserNameAndPassword(String userName, String password) {
        UserEntity userEntity = getByUserName(userName);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public boolean checkLeaderAndMember(HttpServletRequest request, UserEntity userEntity, String userName){
        String token = tokenUtil.getTokenFromRequest(request);
        UserResponseOfUser userResponseOfUser = tokenUtil.getJwtToUserResponse(token);
        UserEntity userEntityJwt = getByUserName(userResponseOfUser.getUserName());
        Set<Integer> listIdLeader = teamService.getIdLeader();
        if (listIdLeader.contains(userEntityJwt.getId())){
            Set<Integer> listIdMemberOfLeader = teamService.getIdMemberByIdLeader(userEntityJwt.getId());
            return listIdMemberOfLeader.contains(userEntity.getId());
        }
        return userResponseOfUser.getUserName().equals(userName);
    }

}
