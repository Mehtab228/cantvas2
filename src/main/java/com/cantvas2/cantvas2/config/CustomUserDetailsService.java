package com.cantvas2.cantvas2.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cantvas2.cantvas2.models.Teacher;
import com.cantvas2.cantvas2.services.DatabaseService;

public class CustomUserDetailsService implements UserDetailsService{
    @Autowired DatabaseService databaseService;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       Teacher teacher = databaseService.teacherRepository.findByUsername(username);
       if(teacher != null){
        return teacher;
       } 
       throw new UsernameNotFoundException("Username Not Found");
    }
}