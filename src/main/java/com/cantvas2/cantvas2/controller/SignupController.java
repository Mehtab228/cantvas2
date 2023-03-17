package com.cantvas2.cantvas2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cantvas2.cantvas2.config.StringlyTypedJdbcUserDetailsManager;
import com.cantvas2.cantvas2.models.*;
// import com.cantvas2.cantvas2.repository.UserRepository;
import com.cantvas2.cantvas2.services.*;


@Controller
@RequestMapping("/signup")
public class SignupController {
  private DatabaseService databaseService;
  private PasswordEncoder passwordEncoder;
  private ConcreteUserFactory userFactory;

  @Autowired
  private StringlyTypedJdbcUserDetailsManager stringlyTypedJdbcUserDetailsManager;

  public SignupController(PasswordEncoder passwordEncoder,
      ConcreteUserFactory userFactory, DatabaseService databaseService) {
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

        UserDetails user = new CantvasUser.Builder()
        .username(form.getUsername())
        .password(passwordEncoder.encode(form.getPassword()))
        .userType(userType)
        .build();
        
        stringlyTypedJdbcUserDetailsManager.createUser(user);
    // if (userType.equalsIgnoreCase("student")) {
    //   Student newStudent = userFactory.createStudent(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getUsername());
    //   databaseService.createStudent(newStudent);
    // } else if (userType.equalsIgnoreCase("teacher")) {
    //   Teacher newTeacher = userFactory.createTeacher(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getUsername());
    //   databaseService.createTeacher(newTeacher);
    // }

    return "redirect:/login";
  }
}
