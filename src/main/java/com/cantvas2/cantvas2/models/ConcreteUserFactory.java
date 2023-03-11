package com.cantvas2.cantvas2.models;

public class ConcreteUserFactory implements UserFactory {
    
    public Student createStudent(String username, String password, String name) {
        return new Student(username, password, name);
    }

    public Teacher createTeacher(String username, String password, String name) {
        return new Teacher(username, password, name);
    }

    public Admin createAdmin(String name) {
        return new Admin();
    }

}
