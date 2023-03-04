package com.cantvas2.cantvas2.models;

public class TeacherFactory extends AbstractCantvasUserFactory {
    @Override
    public Teacher createTeacher() {
        return new Teacher();
    }
}
