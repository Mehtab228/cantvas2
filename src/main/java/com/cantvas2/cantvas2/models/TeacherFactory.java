package com.cantvas2.cantvas2.models;

public class TeacherFactory extends AbstractCantvasUserFactory {
    @Override
    public Teacher createTeacher(String name) {
        return new Teacher(name);
    }
}
