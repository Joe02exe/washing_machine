package com.waschmaschine.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Set;

@Entity(name = "")
public class Person implements Serializable {

    @Id
    @Column(length = 100)
    private String username;
    private String password;
    @Column(length = 100)
    private String lastname;
    @Column(length = 100)
    private String firstname;
    private String email;

    @Enumerated(EnumType.STRING)
    private Floor floor;
    @OneToMany(mappedBy = "person")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Event> events;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public Floor getFloor() {
        return floor;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;

        return username.equals(person.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
