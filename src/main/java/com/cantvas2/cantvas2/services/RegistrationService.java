package com.cantvas2.cantvas2.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cantvas2.cantvas2.models.*;

@Service
public class RegistrationService {

    private final StudentFactory studentFactory;
    private final TeacherFactory teacherFactory;
    
    @Autowired
    RegistrationService(StudentFactory studentFactory, TeacherFactory teacherFactory) {
        this.studentFactory = studentFactory;
        this.teacherFactory = teacherFactory;
    }
}
