package com.cantvas2.cantvas2.models;

public interface UserFactory {

    public Student createStudent(String name);

    public Teacher createTeacher(String name);
    
    public Admin createAdmin(String name);

    public CantvasUser createUser(String username, String password);
}
