package com.cantvas2.cantvas2.models;

public abstract class AbstractCantvasUserFactory {

    public Student createStudent(String name) {
        throw new UnsupportedOperationException("abstract student constructor cannot be invoked directly.");
    }

    public Teacher createTeacher(String name) {
        throw new UnsupportedOperationException("abstract teacher constructor cannot be invoked directly.");
    }
    
    public Admin createAdmin(String name) {
        throw new UnsupportedOperationException("abstract admin constructor cannot be invoked directly.");
    }
}
