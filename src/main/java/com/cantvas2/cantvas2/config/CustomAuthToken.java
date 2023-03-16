package com.cantvas2.cantvas2.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthToken extends UsernamePasswordAuthenticationToken {

    String userType;

    public CustomAuthToken(Object principal, Object credentials, Object userType) {
        super(principal, credentials);
        this.userType = (String) userType;
    }
    
}
