package com.company.adminapi.dto;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUp {
    private int level_up_id;
    private int customer_id;
    private int points;
    private LocalDate member_date;

    public int getLevel_up_id() {
        return level_up_id;
    }

    public void setLevel_up_id(int level_up_id) {
        this.level_up_id = level_up_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDate getMember_date() {
        return member_date;
    }

    public void setMember_date(LocalDate member_date) {
        this.member_date = member_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUp levelUp = (LevelUp) o;
        return getLevel_up_id() == levelUp.getLevel_up_id() &&
                getCustomer_id() == levelUp.getCustomer_id() &&
                getPoints() == levelUp.getPoints() &&
                getMember_date().equals(levelUp.getMember_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevel_up_id(), getCustomer_id(), getPoints(), getMember_date());
    }

    @Override
    public String toString() {
        return "LevelUp{" +
                "level_up_id=" + level_up_id +
                ", customer_id=" + customer_id +
                ", points=" + points +
                ", member_date=" + member_date +
                '}';
    }
}
