package com.example.demo.utils;

import com.example.demo.models.outs.UserResponseOfUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@Component
public class TokenUtil {

    public static final String AUTHORIZATION = "Authorization";

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    public UserResponseOfUser getJwtToUserResponse(String token) {
        UserResponseOfUser userResponseOfUser = new UserResponseOfUser();
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("$(security.authentication.jwt.secret-key)"))
                .parseClaimsJws(token).getBody();
        Map<String, Object> expectedMap = new HashMap<>(claims);
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        userResponseOfUser.setUserName((String) expectedMap.get("sub"));
        return userResponseOfUser;
    }
}
