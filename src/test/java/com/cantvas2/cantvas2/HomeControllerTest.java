package com.cantvas2.cantvas2;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
// @WebMvcTest(HomeController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testHomePage() throws Exception{
    mockMvc.perform(get(("/")))
      .andExpect(status().isOk())
      .andExpect(view().name("home"))
      .andExpect(content().string(containsString("Home Page")));
  }

  @Test
  public void testLoginPage() throws Exception{
    mockMvc.perform(get("/login"))
    .andExpect(status().isOk())
    .andExpect(content().string(containsString("Log in")));
  }

  @Test
  public void testSignupPage() throws Exception{
    mockMvc.perform(get("/signup"))
    .andExpect(status().isOk())
    .andExpect(content().string(containsString("Sign")));
  }

}
