package com.cantvas2.cantvas2.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import static java.time.Month.*;

import com.cantvas2.cantvas2.models.Course;
import com.cantvas2.cantvas2.services.DatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courses")
public class CourseController {
  @Autowired DatabaseService databaseService;
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
    List<LocalDate> october = java.createCalendar()
        .getAssignments()
        .keySet()
        .stream()
        .filter(date -> date.getMonth() == OCTOBER)
        .sorted()
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

  @GetMapping("/{id}")
  @ResponseBody 
  public Course getCourseById(@PathVariable(value = "id") Long courseId){
    return databaseService.findById(courseId).get();
  }
}
