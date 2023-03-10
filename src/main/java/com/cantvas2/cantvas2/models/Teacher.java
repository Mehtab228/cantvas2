package com.cantvas2.cantvas2.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Entity
@RequiredArgsConstructor
public class Teacher extends CantvasUser {
    
    @Getter
    final String name;

    public Teacher(String username, String password, String name){
      super(username, password);
      this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_TEACHER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}