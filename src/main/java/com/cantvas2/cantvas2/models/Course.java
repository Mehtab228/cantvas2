package com.cantvas2.cantvas2.models;

import java.time.LocalDate;
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

  public final static class Builder extends AbstractCourseBuilder {
    String name;
    String desc;
    LocalDate startDate;
    LocalDate endDate;

    public Builder() {
    }

    public AbstractCourseBuilder name(String name) {
      this.name = name;
      return this;
    }

    public AbstractCourseBuilder desc(String desc) {
      this.desc = desc;
      return this;
    }

    public AbstractCourseBuilder startDate(LocalDate startDate) {
      this.startDate = startDate;
      return this;
    }

    public AbstractCourseBuilder endDate(LocalDate endDate) {
      this.endDate = endDate;
      return this;
    }

    public Course build() {
      return new Course(this.name, this.desc);
    }
  }

  @OneToMany(cascade = CascadeType.ALL)
  List<Student> users;

  @Override
  public Iterator<LocalDate> iterator() {
    return startDate.datesUntil(endDate).iterator();
  }

  public Calendar createCalendar() {
    Map<LocalDate, List<Assignment>> assignments = new HashMap<>();
    Calendar calendar = new Calendar();
    this.iterator().forEachRemaining(day -> {
      assignments.put(day, new LinkedList<>());
    });
    calendar.assignments = assignments;
    return calendar;
  }
}
