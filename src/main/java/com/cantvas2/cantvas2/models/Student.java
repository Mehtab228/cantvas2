package com.cantvas2.cantvas2.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@RequiredArgsConstructor
public class Student extends CantvasUser {
    @Getter
    final String name;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_STUDENT"));
      }
}
