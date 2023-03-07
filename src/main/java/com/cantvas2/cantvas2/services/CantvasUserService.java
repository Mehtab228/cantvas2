package com.cantvas2.cantvas2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CantvasUserService {
  @Autowired JdbcTemplate jdbcTemplate;

  
}