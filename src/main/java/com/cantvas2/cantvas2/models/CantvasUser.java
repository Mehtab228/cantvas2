package com.cantvas2.cantvas2.models;

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
public class CantvasUser {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  final String username;
  final String password;

  @OneToMany(cascade = CascadeType.ALL)
  List<Course> courses;
}
