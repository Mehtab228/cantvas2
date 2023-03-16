package com.cantvas2.cantvas2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDate;
import static java.time.Month.*;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.cantvas2.cantvas2.config.SecurityConfig;
import com.cantvas2.cantvas2.models.Course;
import com.cantvas2.cantvas2.models.Teacher;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { Cantvas2Application.class,
        SecurityConfig.class })
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "test")
public class CourseBuilderTests {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "david", roles = { "FAKE_ROLE" })
    void testCourseBuilder() throws Exception {
        Course builder = Course.builder()
                .name("Java 401")
                .desc("Software development with Spring")
                // .startDate(LocalDate.of(2022, OCTOBER, 23))
                // .endDate(LocalDate.of(2022, DECEMBER, 30))
                .build();
        mockMvc.perform(post("/courses/new")
                .contentType("application/json")
                .content(mapper.writeValueAsString(builder)))
                .andExpect(status().isCreated());
    }
}
