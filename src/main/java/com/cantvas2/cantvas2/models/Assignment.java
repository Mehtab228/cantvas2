package com.cantvas2.cantvas2.models;

import java.time.LocalDateTime;

import com.cantvas2.cantvas2.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Assignment implements Copyable, Comparable<LocalDateTime> {
    final LocalDateTime dueDate;
    final LocalDateTime availableFrom;
    final String name;
    final String description;

    public Object copy() {
        return new Assignment(dueDate, availableFrom, name, description);
    }

    @Override
    public int compareTo(LocalDateTime o) {
        return this.dueDate.compareTo(o);
    }
}
