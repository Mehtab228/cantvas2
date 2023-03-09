package com.cantvas2.cantvas2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cantvas2.cantvas2.models.*;
import com.cantvas2.cantvas2.config.*;

@ContextConfiguration(classes = { RegistrationConfig.class })
@SpringBootTest
public class UserFactoryTests {

    @Autowired
    RegistrationConfig userConfig;
    UserFactory userFactory = new ConcreteUserFactory();

    @Test
    void testStudentFactory() {
        assertEquals("[ROLE_STUDENT]", userFactory.createStudent("Ben").getAuthorities().toString());
    }

    @Test
    void testTeacherFactory() {
        assertEquals("[ROLE_TEACHER]", userFactory.createTeacher("David").getAuthorities().toString());
    }
}
