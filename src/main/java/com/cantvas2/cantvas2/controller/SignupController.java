package com.cantvas2.cantvas2.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cantvas2.cantvas2.models.CantvasUser;
import com.cantvas2.cantvas2.models.ConcreteUserFactory;
import com.cantvas2.cantvas2.models.SignupForm;

@Controller
@RequestMapping("/signup")
public class SignupController {
  private JdbcUserDetailsManager jdbcUserDetailsManager;
  private PasswordEncoder passwordEncoder;
  private ConcreteUserFactory userFactory;

  public SignupController(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder, ConcreteUserFactory userFactory){
    this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    this.passwordEncoder = passwordEncoder;
    this.userFactory = userFactory;
  }

  @GetMapping
  public String signupForm(){
    return "signup";
  }

  @PostMapping
  public String processSignup(SignupForm form){
    CantvasUser newUser = userFactory.createUser(form.getUsername(), passwordEncoder.encode(form.getPassword()));
    jdbcUserDetailsManager.createUser(newUser);
    return "redirect:/login";
  }
}
