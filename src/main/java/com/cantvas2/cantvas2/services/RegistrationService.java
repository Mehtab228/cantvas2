package com.cantvas2.cantvas2.services;

import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.*;

@Service
public class RegistrationService {

    public final UserFactory userFactory;
    private final DatabaseService databaseService;
    
    RegistrationService(UserFactory userFactory, DatabaseService databaseService) {
        this.userFactory = userFactory;
        this.databaseService = databaseService;
    }

    void enrollStudent(String name) {
        Student student = userFactory.createStudent(name);
        databaseService.enrollStudent(student);
    }
}
