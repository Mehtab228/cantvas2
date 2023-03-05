package com.cantvas2.cantvas2.controller;

import java.util.List;

import com.cantvas2.cantvas2.models.Course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {
  @ModelAttribute
  public void AddCoursesToModel(Model model){
    List<Course> coursesList = List.of(new Course("Java 401", "Advanced Java course with Spring and Android"),
    new Course("JavaScript 401", "Advanced JavaScript course going deep into React and Node.js"),
    new Course("JavaScript 201", "Introductory JavaScript"));

    model.addAttribute("courses", coursesList);
  }

  @GetMapping
  public String GetCourse(){
    return "courses";
  }
}
