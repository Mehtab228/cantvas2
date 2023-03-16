package com.cantvas2.cantvas2.config;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTypeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public UserTypeAuthenticationFilter(ProviderManager providerManager) {
        setAuthenticationManager(providerManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = getAuthRequest(request);
        setDetails(request, authToken);

        return this.getAuthenticationManager().authenticate(authToken);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String userType = obtainUserType(request);

        return new CustomAuthToken(username, password, userType);
    }

    private String obtainUserType(HttpServletRequest request) {
        return request.getParameter("user-radio");
    }
}
