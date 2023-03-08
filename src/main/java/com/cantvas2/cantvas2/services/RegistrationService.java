package com.cantvas2.cantvas2.services;

import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.*;

@Service
public class RegistrationService {

    public final StudentFactory studentFactory;
    public final TeacherFactory teacherFactory;
    private final DatabaseService databaseService;
    
    RegistrationService(StudentFactory studentFactory, TeacherFactory teacherFactory, DatabaseService databaseService) {
        this.studentFactory = studentFactory;
        this.teacherFactory = teacherFactory;
        this.databaseService = databaseService;
    }

    void enrollStudent(String name) {
        Student student = studentFactory.createStudent(name);
        databaseService.enrollStudent(student);
    }
}
