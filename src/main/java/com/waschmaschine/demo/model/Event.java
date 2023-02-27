package com.waschmaschine.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Person person;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private int minutesWashed;

    public Long getId() {
        return id;
    }

    @JsonBackReference
    public Person getUser() {
        return person;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getMinutesWashed() {
        return minutesWashed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Person person) {
        this.person = person;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setMinutesWashed(int minutesWashed) {
        this.minutesWashed = minutesWashed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id.equals(event.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
