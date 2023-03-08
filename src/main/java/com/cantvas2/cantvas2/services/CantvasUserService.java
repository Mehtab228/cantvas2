package com.cantvas2.cantvas2.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.CantvasUser;
import com.cantvas2.cantvas2.models.Student;

@Service
public class CantvasUserService {
  @Autowired JdbcTemplate jdbcTemplate;

  public void saveOne(Student user){
    jdbcTemplate.update("insert into CANTVAS_USER (username) values (?)", user.getUsername());
  }
}