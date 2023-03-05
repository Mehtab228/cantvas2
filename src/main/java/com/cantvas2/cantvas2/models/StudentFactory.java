package com.cantvas2.cantvas2.models;

public class StudentFactory extends AbstractCantvasUserFactory {
    @Override
    public Student createStudent(String name) {
        return new Student(name);
    }
}
