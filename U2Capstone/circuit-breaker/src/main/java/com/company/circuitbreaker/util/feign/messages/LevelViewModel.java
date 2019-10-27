package com.company.circuitbreaker.util.feign.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class LevelViewModel {
    private int level_up_id;
    private int customer_id;
    private Integer points;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate member_date;
    private String circuit_breaker_message;

    public LevelViewModel(int level_up_id, int customer_id, Integer points, LocalDate member_date, String circuit_breaker_message) {
        this.level_up_id = level_up_id;
        this.customer_id = customer_id;
        this.points = points;
        this.member_date = member_date;
        this.circuit_breaker_message = circuit_breaker_message;
    }

    public LevelViewModel(int customer_id, Integer points, LocalDate member_date, String circuit_breaker_message) {
        this.customer_id = customer_id;
        this.points = points;
        this.member_date = member_date;
        this.circuit_breaker_message = circuit_breaker_message;
    }

    public LevelViewModel(int level_up_id, int customer_id, Integer points, LocalDate member_date) {
        this.level_up_id = level_up_id;
        this.customer_id = customer_id;
        this.points = points;
        this.member_date = member_date;
    }

    public LevelViewModel(int customer_id, Integer points, LocalDate member_date) {
        this.customer_id = customer_id;
        this.points = points;
        this.member_date = member_date;
    }

    public LevelViewModel(){}

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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDate getMember_date() {
        return member_date;
    }

    public void setMember_date(LocalDate member_date) {
        this.member_date = member_date;
    }

    public String getCircuit_breaker_message() {
        return circuit_breaker_message;
    }

    public void setCircuit_breaker_message(String circuit_breaker_message) {
        this.circuit_breaker_message = circuit_breaker_message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelViewModel that = (LevelViewModel) o;
        return level_up_id == that.level_up_id &&
                customer_id == that.customer_id &&
                points.equals(that.points) &&
                member_date.equals(that.member_date) &&
                Objects.equals(circuit_breaker_message, that.circuit_breaker_message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level_up_id, customer_id, points, member_date, circuit_breaker_message);
    }

    @Override
    public String toString() {
        return "LevelViewModel{" +
                "level_up_id=" + level_up_id +
                ", customer_id=" + customer_id +
                ", points=" + points +
                ", member_date=" + member_date +
                ", circuit_breaker_message='" + circuit_breaker_message + '\'' +
                '}';
    }
}
