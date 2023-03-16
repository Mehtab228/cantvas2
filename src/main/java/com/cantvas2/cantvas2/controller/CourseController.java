package com.cantvas2.cantvas2.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static java.time.Month.*;

import com.cantvas2.cantvas2.models.*;

import com.cantvas2.cantvas2.services.DatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courses")
public class CourseController {


  @Autowired
  DatabaseService databaseService;


  @ModelAttribute
  public void AddCoursesToModel(Model model) {
    List<Course> coursesList = List.of(new Course("Java 401", "Advanced Java course with Spring and Android"),
        new Course("JavaScript 401", "Advanced JavaScript course going deep into React and Node.js"),
        new Course("JavaScript 201", "Introductory JavaScript"));

    model.addAttribute("courses", coursesList);
  }

  @ModelAttribute
  public void displayCalendar(Model model) {
    Course java = new Course("Java 401", "Advanced Java course with Spring and Android");
    java.setStartDate(LocalDate.of(2022, OCTOBER, 10));
    java.setEndDate(LocalDate.of(2022, DECEMBER, 10));
    List<Object> october = java.createCalendar()
        .getAssignments()
        .entrySet()
        .stream()
        .filter(date -> date.getKey().getMonth() == OCTOBER)
        .sorted(Comparator.comparing(Map.Entry<LocalDate, List<Assignment>>::getKey))
        .map(m -> {
          String name = m.getValue().get(0).getName();
          LocalDateTime dueDate = m.getValue().get(0).getDueDate();
          return name + ": " + dueDate.truncatedTo(ChronoUnit.HOURS).toString();
        })
        .collect(Collectors.toUnmodifiableList());
    model.addAttribute("calendar", october);
  }

  @GetMapping
  public String GetCourse() {
    return "courses";
  }

  @GetMapping("/calendar")
  public String getCourseCalendar() {
    return "courseCalendar";
  }

  @PostMapping(path = "/enroll/{id}", consumes = "application/json")
  @ResponseBody
  public Student enrollStudent(@RequestBody Student student,
      @PathVariable(value = "id") Long courseId, @AuthenticationPrincipal Teacher teacher) {
    Optional<Course> course = databaseService.findCourseByTeacherId(teacher)
        .flatMap(_course -> {
          // _course.enrollStudent(student);
          return Optional.of(_course);
        });
    databaseService.updateCourse(course.get());
    return student;
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Course getCourseById(@PathVariable(value = "id") Long courseId) {

    return databaseService.findCourseById(courseId).get();
  }

  @PostMapping(path = "/new", consumes = "application/json")
  public void createCourse(@RequestBody Course course) {
    databaseService.updateCourse(course);
  }
}
