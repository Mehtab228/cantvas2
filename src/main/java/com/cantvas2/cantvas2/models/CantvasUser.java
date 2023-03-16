package com.cantvas2.cantvas2.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Data // d
@Entity // e
@Table(name = "users") // t
@RequiredArgsConstructor
public class CantvasUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  final String username;
  final String password;
  final String userType;
  String authoritiesStr;
  boolean enabled = true;

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public void setAuthorities(String authority) {
    this.authoritiesStr += "," + authority;
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
    private Collection<GrantedAuthority> authorities;
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

    public Builder authorities(String authorities) {
      this.authorities = Arrays.asList(new SimpleGrantedAuthority(authorities));
      return this;
    }

    public CantvasUser build() {
      return new CantvasUser(this.username, this.password, this.userType);
    }
  }
}
