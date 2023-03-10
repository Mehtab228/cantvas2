package com.cantvas2.cantvas2.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cantvas2.cantvas2.models.*;
import com.cantvas2.cantvas2.services.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignupController {
  private JdbcUserDetailsManager jdbcUserDetailsManager;
  private DatabaseService databaseService;
  private PasswordEncoder passwordEncoder;
  private ConcreteUserFactory userFactory;

  public SignupController(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder,
      ConcreteUserFactory userFactory, DatabaseService databaseService) {
    this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    this.passwordEncoder = passwordEncoder;
    this.userFactory = userFactory;
    this.databaseService = databaseService;
  }

  @GetMapping
  public String signupForm(Model model) {
    model.addAttribute("signupForm", new SignupForm());
    return "signup";
  }

  @PostMapping
  public String processSignup(@ModelAttribute("signupForm") SignupForm form,
      @RequestParam("user-radio") String userType) {
    CantvasUser newUser = userFactory.createUser(form.getUsername(), passwordEncoder.encode(form.getPassword()));
    if (userType.equalsIgnoreCase("student")) {
      Student newStudent = userFactory.createStudent(form.getUsername());
      databaseService.createStudent(newStudent);
    } else if (userType.equalsIgnoreCase("teacher")) {
      Teacher newTeacher = userFactory.createTeacher(form.getUsername());
      databaseService.createTeacher(newTeacher);
    }
    jdbcUserDetailsManager.createUser(newUser);
    return "redirect:/login";
  }
}
