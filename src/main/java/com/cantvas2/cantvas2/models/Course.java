package com.cantvas2.cantvas2.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Course {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  final String name;
  final String desc;
  LocalDate startDate;
  LocalDate endDate;

  @OneToMany(cascade = CascadeType.ALL)
  List<CantvasUser> users;

  public Course(String name, String desc){
    this.name = name;
    this.desc = desc;
  }
}
