package com.cantvas2.cantvas2.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Student extends CantvasUser {
    @Getter
    final String name;

    public Student(String username, String password, String name) {
      super(username, password);
      this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_STUDENT"));
      }
}
