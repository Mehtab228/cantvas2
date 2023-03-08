package com.cantvas2.cantvas2;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.cantvas2.cantvas2.models.*;
import com.cantvas2.cantvas2.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { Cantvas2Application.class })
@AutoConfigureMockMvc(addFilters = false)
public class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegistrationService registrationService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "david", roles = { "ADMIN" })
    void canEnrollStudentUsingAdminRole() throws Exception {
        Student student = registrationService.studentFactory.createStudent("Ben");

        String json = mapper.writeValueAsString(student);

        mockMvc.perform(put("/courses/enroll/1")
                .contentType("application/json")
                .content(json)
                .characterEncoding("utf-8")).andExpect(status().is2xxSuccessful());
    }
}
