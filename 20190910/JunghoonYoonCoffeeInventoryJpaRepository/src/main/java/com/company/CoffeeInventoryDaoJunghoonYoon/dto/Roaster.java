package com.company.CoffeeInventoryDaoJunghoonYoon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="roaster")
public class Roaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roasterId;
    private String name;
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String phone;
    private String email;
    private String note;

    @OneToMany(mappedBy = "roasterId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Roaster> roaster;


    public Set<Roaster> getRoaster() {
        return roaster;
    }

    public void setRoaster(Set<Roaster> roaster) {
        this.roaster = roaster;
    }

    public int getRoasterId() {
        return roasterId;
    }

    public void setRoasterId(int roasterId) {
        this.roasterId = roasterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roaster roaster = (Roaster) o;
        return roasterId == roaster.roasterId &&
                name.equals(roaster.name) &&
                street.equals(roaster.street) &&
                city.equals(roaster.city) &&
                state.equals(roaster.state) &&
                postal_code.equals(roaster.postal_code) &&
                phone.equals(roaster.phone) &&
                email.equals(roaster.email) &&
                note.equals(roaster.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roasterId, name, street, city, state, postal_code, phone, email, note);
    }
}
