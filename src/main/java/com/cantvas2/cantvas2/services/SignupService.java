package com.cantvas2.cantvas2.services;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.*;

@Service
public class SignupService {

    public final UserFactory userFactory;
    private final DatabaseService databaseService;
    private final JdbcUserDetailsManager userDetailsManager;
    
    SignupService(UserFactory userFactory, DatabaseService databaseService, JdbcUserDetailsManager userDetailsManager) {
        this.userFactory = userFactory;
        this.databaseService = databaseService;
        this.userDetailsManager = userDetailsManager;
    }
}
