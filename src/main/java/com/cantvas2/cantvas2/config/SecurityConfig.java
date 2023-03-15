package com.cantvas2.cantvas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import java.util.List;

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;

@Configuration
@EnableWebSecurity
@Profile(value = { "test", "development", "production" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf().disable()
        .cors().disable()
        .addFilterBefore(new UsernamePasswordAuthenticationFilter(
            new ProviderManager(List.of(authenticationProvider()))),
            UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> {
          auth.mvcMatchers("/", "/login").permitAll()
              .mvcMatchers("/courses/**").authenticated();
        }).formLogin(form -> {
          form.loginPage("/login").successForwardUrl("/courses");
        }).oauth2Login(oauth -> {
          oauth.loginPage("/login").defaultSuccessUrl("/courses");
        });
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(toH2Console());
  }

  @Bean
  @Profile(value = { "test", "development" })
  JdbcUserDetailsManager jdbcUserDetailsService(PasswordEncoder passwordEncoder) {
    JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(
        new DriverManagerDataSource("jdbc:h2:mem:cantvas", "sa", ""));
    UserDetails user = User.builder()
        .username("ben")
        .password(passwordEncoder.encode("foobar"))
        .authorities("USER")
        .build();
    UserDetails admin = User.builder()
        .username("david")
        .password(passwordEncoder.encode("bazquux"))
        .authorities("TEACHER, ADMIN")
        .build();
    jdbc.createUser(user);
    jdbc.createUser(admin);
    return jdbc;
  }

  @Bean
  @Profile("test")
  H2ConsoleProperties h2ConsoleProperties() {
    return new H2ConsoleProperties();
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Override
  // public void configure(AuthenticationManagerBuilder auth) throws Exception {
  // auth.userDetailsService(jdbcUserDetailsService()).passwordEncoder(passwordEncoder());
  // }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsService(passwordEncoder()));
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

}
