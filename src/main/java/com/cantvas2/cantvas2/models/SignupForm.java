package com.cantvas2.cantvas2.models;

import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class SignupForm {
  private String username;
  private String password;
  private String name;

  
}
