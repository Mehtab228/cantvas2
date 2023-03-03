package com.cantvas2.cantvas2.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Course {
  final String courseName;
  final String courseDescription;
  final LocalDate startDate;
  final LocalDate endDate;

  @OneToMany(cascade = CascadeType.ALL)
  List<CantvasUser> users;
}
