package com.cantvas2.cantvas2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import com.cantvas2.cantvas2.models.*;

@ContextConfiguration
@SpringBootTest
public class UserFactoryTests {

    @Autowired StudentFactory studentFactory;
    @Autowired TeacherFactory teacherFactory;

    @Configuration
    static class Config {
        @Bean
        StudentFactory studentFactory() {
            return new StudentFactory();
        }

        @Bean
        TeacherFactory teacherFactory() {
            return new TeacherFactory();
        }
    }

    @Test
    void testStudentFactory() {
        assertEquals("[ROLE_STUDENT]", studentFactory.createStudent("Ben").getAuthorities().toString());
    }

    @Test
    void testAutowire() {
        assertDoesNotThrow(() -> studentFactory.createStudent("Ben"));
    }

    @Test
    void testTeacherFactory() {
        assertEquals("[ROLE_TEACHER, ROLE_ADMIN]", teacherFactory.createTeacher("David").getAuthorities().toString());
    }
}
