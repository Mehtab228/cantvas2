package com.cantvas2.cantvas2.models;

import java.time.LocalDate;
import static java.time.Month.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Course implements Iterable<LocalDate> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  final String name;
  final String desc;
  LocalDate startDate;
  LocalDate endDate;

  public final class Calendar {
    @Getter
    Map<LocalDate, List<Assignment>> assignments;
  }

  public final static class Builder {
    private String name;
    private String desc;
    private LocalDate startDate;
    private LocalDate endDate;

    public Builder() {
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder desc(String desc) {
      this.desc = desc;
      return this;
    }

    public Builder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public Builder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public Course build() {
      return new Course(this.name, this.desc);
    }
  }

  @OneToMany(cascade = CascadeType.ALL)
  List<Student> students;

  @Override
  public Iterator<LocalDate> iterator() {
    return startDate.datesUntil(endDate).iterator();
  }

  public Calendar createCalendar() {
    Map<LocalDate, List<Assignment>> assignments = new HashMap<>();
    Calendar calendar = new Calendar();
    this.iterator().forEachRemaining(day -> {
      List<Assignment> test = new LinkedList<>();
      test.add(new Assignment(LocalDateTime.of(day, LocalTime.now()),
          LocalDateTime.of(day.plusDays(1), LocalTime.MIDNIGHT), "Test Assignment", "Test description"));
      assignments.put(day, test);
    });
    calendar.assignments = assignments;
    return calendar;
  }

  public void enrollStudent(Student student) {
    students.add(student);
  }
}
