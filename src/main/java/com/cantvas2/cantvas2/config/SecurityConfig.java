package com.cantvas2.cantvas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

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
        .authorizeHttpRequests(auth -> {
          auth.mvcMatchers("/", "/login").permitAll()
          .mvcMatchers("/courses/**").authenticated();
        }).formLogin().loginPage("/login");
  }

  @Bean
  WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(toH2Console());
  }

  @Bean
  @Profile(value = { "test", "development" })
  JdbcUserDetailsManager jdbcUserDetailsService() {
    return new JdbcUserDetailsManager(new DriverManagerDataSource("jdbc:h2:mem:cantvas", "sa", ""));
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

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(jdbcUserDetailsService()).passwordEncoder(passwordEncoder());
  }
}
