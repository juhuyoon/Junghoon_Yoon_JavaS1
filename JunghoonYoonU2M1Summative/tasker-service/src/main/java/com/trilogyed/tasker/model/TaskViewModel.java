package com.trilogyed.tasker.model;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

public class TaskViewModel {
    private int id;
    @NotEmpty(message = "Give a description")
    private String description;
    @NotEmpty(message = "Give a create date")
    private LocalDate createDate;
    @NotEmpty(message = "Give a due date")
    private LocalDate dueDate;
    @NotEmpty(message = "Give a category")
    private String category;
    private String advertisement;

    public TaskViewModel() {
    }

    public TaskViewModel(int id, @NotEmpty(message = "Give a description") String description, @NotEmpty(message = "Give a create date") LocalDate createDate, @NotEmpty(message = "Give a due date") LocalDate dueDate, @NotEmpty(message = "Give a category") String category, String advertisement) {
        this.id = id;
        this.description = description;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.category = category;
        this.advertisement = advertisement;
    }

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

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskViewModel that = (TaskViewModel) o;
        return id == that.id &&
                description.equals(that.description) &&
                createDate.equals(that.createDate) &&
                dueDate.equals(that.dueDate) &&
                category.equals(that.category) &&
                advertisement.equals(that.advertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createDate, dueDate, category, advertisement);
    }

    @Override
    public String toString() {
        return "TaskViewModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", dueDate=" + dueDate +
                ", category='" + category + '\'' +
                ", advertisement='" + advertisement + '\'' +
                '}';
    }
}
