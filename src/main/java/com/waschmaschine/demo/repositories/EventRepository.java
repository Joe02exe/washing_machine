package com.waschmaschine.demo.repositories;

import com.waschmaschine.demo.model.Event;
import com.waschmaschine.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long> {
    Collection<Event> findAllByPerson(Person person);

    Event findEventById(Long id);

    void deleteEventById(Long id);
}
