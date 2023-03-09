package com.cantvas2.cantvas2.services;

import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.*;

@Service
public class RegistrationService {

    public final UserFactory userFactory;
    private final DatabaseService databaseService;
    private final JdbcUserDetailsManager userDetailsManager;
    
    RegistrationService(UserFactory userFactory, DatabaseService databaseService, JdbcUserDetailsManager userDetailsManager) {
        this.userFactory = userFactory;
        this.databaseService = databaseService;
        this.userDetailsManager = userDetailsManager;
    }

    // void enrollStudent(String name) {
    //     Student student = userFactory.createStudent(name);
    //     databaseService.enrollStudent(student);
    // }

    void createTeacher(String name) {
        Teacher teacher = userFactory.createTeacher(name);
        databaseService.jdbcTemplate.update("insert into Teacher (id, name) values (?, ?)", 1, name);
        userDetailsManager.createUser(teacher);
    }
}
