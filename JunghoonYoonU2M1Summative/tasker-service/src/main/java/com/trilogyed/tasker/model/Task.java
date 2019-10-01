package com.trilogyed.tasker.model;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private int id;
    @NotEmpty(message = "Supply a description")
    private String description;
    @NotEmpty(message = "Enter a create date")
    private LocalDate createDate;
    @NotEmpty(message = "Enter a due date")
    private LocalDate dueDate;
    @NotEmpty(message = "Enter a category")
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                description.equals(task.description) &&
                createDate.equals(task.createDate) &&
                dueDate.equals(task.dueDate) &&
                category.equals(task.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createDate, dueDate, category);
    }
}
