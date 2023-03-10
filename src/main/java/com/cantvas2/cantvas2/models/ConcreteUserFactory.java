package com.cantvas2.cantvas2.models;

public class ConcreteUserFactory implements UserFactory {
    
    public Student createStudent(String name) {
        return new Student(name);
    }

    public Teacher createTeacher(String name) {
        return new Teacher(name);
    }

    public Admin createAdmin(String name) {
        return new Admin();
    }

    public CantvasUser createUser(String username, String password){
        return new CantvasUser(username, password);
    }
}
