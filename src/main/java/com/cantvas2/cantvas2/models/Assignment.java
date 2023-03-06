package com.cantvas2.cantvas2.models;

import java.time.LocalDateTime;

import com.cantvas2.cantvas2.util.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class Assignment implements Copyable {
    final LocalDateTime dueDate;
    final LocalDateTime availableFrom;
    final String name;
    final String description;

    public Object copy() {
        return new Assignment(dueDate, availableFrom, name, description);
    }
}
