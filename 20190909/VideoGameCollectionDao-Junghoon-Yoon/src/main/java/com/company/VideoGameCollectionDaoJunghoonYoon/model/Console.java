package com.company.VideoGameCollectionDaoJunghoonYoon.model;

import java.util.Objects;

public class Console {
    private int console_id;
    private String name;
    private String year;

    public int getConsole_id() {
        return console_id;
    }

    public void setConsole_id(int console_id) {
        this.console_id = console_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return console_id == console.console_id &&
                year == console.year &&
                name.equals(console.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(console_id, name, year);
    }
}
