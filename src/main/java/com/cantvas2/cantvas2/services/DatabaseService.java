package com.cantvas2.cantvas2.services;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.cantvas2.cantvas2.models.*;
import com.cantvas2.cantvas2.repository.*;

@Service
public class DatabaseService {

  final StudentRepository studentRepository;
  final TeacherRepository teacherRepository;
  final CourseRepository courseRepository;

  public DatabaseService(StudentRepository studentRepository, TeacherRepository teacherRepository,
      CourseRepository courseRepository) {
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
    this.courseRepository = courseRepository;
  }

  public void saveAll(Iterable<Course> courses) {
    courseRepository.saveAll(courses);
  }

  public Optional<Course> findCourseByTeacherId(Teacher teacher) {
    return courseRepository.findByTeacherId(teacher.getId());
  }

  public Optional<Course> findCourseById(Long id) {
    return courseRepository.findById(id);
  }

  public void updateCourse(Course course) {
    courseRepository.save(course);
  }

  public void createStudent(Student student) {
    studentRepository.save(student);
  }

  public void createTeacher(Teacher teacher) {
    teacherRepository.save(teacher);
  }

}