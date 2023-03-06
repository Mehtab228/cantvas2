package com.cantvas2.cantvas2.models;

import java.time.LocalDate;

public abstract class AbstractCourseBuilder {
    public abstract AbstractCourseBuilder name(String name);
    public abstract AbstractCourseBuilder desc(String desc);
    public abstract AbstractCourseBuilder startDate(LocalDate startDate);
    public abstract AbstractCourseBuilder endDate(LocalDate endDate);
    public abstract Course build();
}
