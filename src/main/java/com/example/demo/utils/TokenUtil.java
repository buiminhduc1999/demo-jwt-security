package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.StringUtils.hasText;

public class TokenUtil {

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }



}
