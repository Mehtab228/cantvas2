package com.cantvas2.cantvas2.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.Course;

@Service
public class DatabaseService {
   @Autowired JdbcTemplate jdbcTemplate;

   public Optional<Course> findById(Long id){
        List<Course> results = jdbcTemplate.query("select * FROM Course WHERE id = ?", this::mapRow, id);
        return results.size() == 0 ?
        Optional.empty():
        Optional.of(results.get(0));
   }

   private Course mapRow(ResultSet resultSet, int rowNumber) throws SQLException{
        return new Course(resultSet.getString("name"), resultSet.getString("desc"));
   }

   public void saveAll(Course courses){
        jdbcTemplate.update("insert into COURSE (name, desc) values (?,?)", courses.getName(), courses.getDesc());
   }
}