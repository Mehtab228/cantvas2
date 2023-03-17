package com.cantvas2.cantvas2.models;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class CantvasUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  final String username;
  final String password;
  final String userType;
  boolean enabled = true;

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    if (this.userType == "STUDENT") {
      return Arrays.asList(new SimpleGrantedAuthority("ROLE_STUDENT"));
    } else if (this.userType == "TEACHER") {
      return Arrays.asList(new SimpleGrantedAuthority("ROLE_TEACHER"));
    }
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

  public final static class Builder {
    private String username;
    private String password;
    private String userType;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public Builder() {    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder password(String password) {
      this.password = encoder.encode(password);
      return this;
    }

    public Builder userType(String userType) {
      this.userType = userType;
      return this;
    }

    public CantvasUser build() {
      return new CantvasUser(this.username, this.password, this.userType);
    }
  }
}
