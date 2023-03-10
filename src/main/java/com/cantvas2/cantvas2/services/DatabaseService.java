package com.cantvas2.cantvas2.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.cantvas2.cantvas2.models.*;
import com.cantvas2.cantvas2.repository.StudentRepository;
import com.cantvas2.cantvas2.repository.TeacherRepository;


@Service
public class DatabaseService {
   @Autowired JdbcTemplate jdbcTemplate;

   @Autowired StudentRepository studentRepository;
   @Autowired TeacherRepository teacherRepository;


   public Optional<Course> findById(Long id){
        List<Course> results = jdbcTemplate.query("select * FROM Course WHERE id = ?", this::mapRow, id);
        return results.size() == 0 ?
        Optional.empty():
        Optional.of(results.get(0));
   }

   private Course mapRow(ResultSet resultSet, int rowNumber) throws SQLException{
        return new Course(resultSet.getString("name"), resultSet.getString("desc"));
   }


   public void saveAll(Iterable<Course> courses){
     for (Course course : courses) {
          jdbcTemplate.update("insert into COURSE (name, desc) values (?,?)", course.getName(), course.getDesc());
     }
   }

   public void createStudent(Student student) {
     studentRepository.save(student);
   }

  public void createTeacher(Teacher teacher) {
    teacherRepository.save(teacher);
  }

  public void updateCourse(Course course) {
    jdbcTemplate.update("insert into Course values (?, ?)", course.getId(), course.getName());
  }

}