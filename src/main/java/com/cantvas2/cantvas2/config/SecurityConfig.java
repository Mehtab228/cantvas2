package com.cantvas2.cantvas2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;

import com.cantvas2.cantvas2.models.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @Profile(value = { "test", "development", "production" })
public class SecurityConfig {

  @Bean
  @Profile("react")
  SecurityFilterChain frontEndSecurityChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
    .csrf(withDefaults())
    .cors(withDefaults())
    .authorizeHttpRequests(auth -> {
      auth.mvcMatchers("/", "/login").permitAll()
          .mvcMatchers("/courses/**").permitAll();}).build();

  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(withDefaults())
        .cors(withDefaults())
        .addFilterBefore(new UserTypeAuthenticationFilter(
            new ProviderManager(List.of(authenticationProvider()))),
            UserTypeAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> {
          auth.mvcMatchers("/", "/login").permitAll()
              .mvcMatchers("/courses/**").authenticated();
        }).formLogin(form -> {
          form.loginPage("/login").successForwardUrl("/courses");
        }).oauth2Login(oauth -> {
          oauth.loginPage("/login").defaultSuccessUrl("/courses");
        }).logout(logout -> logout
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/login"))
        .build();
  }

  @Bean
  @Profile("react")
  WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(toH2Console());
  }

  @Bean
  @Profile(value = { "test", "development", "react" })
  StringlyTypedJdbcUserDetailsManager jdbcUserDetailsService() {
    StringlyTypedJdbcUserDetailsManager jdbc = new StringlyTypedJdbcUserDetailsManager(
        new DriverManagerDataSource("jdbc:h2:mem:cantvas", "sa", ""));
    UserDetails user = new CantvasUser.Builder()
        .username("ben")
        .password("foobar")
        .userType("STUDENT")
        // .passwordEncoder(password -> passwordEncoder().encode(password))
        .authorities("USER")
        .build();
    UserDetails admin = new CantvasUser.Builder()
        .username("david")
        .password("bazquux")
        .userType("TEACHER")
        // .passwordEncoder(password -> passwordEncoder().encode(password))
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
  @Profile("react")
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(jdbcUserDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

}
