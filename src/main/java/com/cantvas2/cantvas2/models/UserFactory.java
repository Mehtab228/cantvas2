package com.cantvas2.cantvas2.models;

public interface UserFactory {

    public Student createStudent(String username, String password, String name);

    public Teacher createTeacher(String username, String password, String name);
    
    public Admin createAdmin(String name);
}
