package com.cantvas2.cantvas2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.cantvas2.cantvas2.models.Course;

@SpringBootTest
public class CourseBuilderTests {

    @Test
    void testCourseBuilder() {
        Course.Builder builder = new Course.Builder();
        assertDoesNotThrow(() -> builder.name("Java 401")
                .desc("Testing the builder pattern in a Spring app")
                .build());
    }
}
