package com.cantvas2.cantvas2.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;
import static java.time.Month.*;

import com.cantvas2.cantvas2.models.Course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {
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
}
