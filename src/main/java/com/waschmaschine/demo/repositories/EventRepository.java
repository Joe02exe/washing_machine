package com.waschmaschine.demo.repositories;

import com.waschmaschine.demo.model.Event;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.model.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long> {
    Collection<Event> findAllByPerson(Person person);

    Event findEventById(Long id);

    void deleteEventById(Long id);

    @Query("SELECT d FROM Event d WHERE d.washingMachine = :washingMachine AND :time BETWEEN d.beginDate AND d.endDate")
    Event getEventWithinTime(@Param("time")LocalDateTime time,@Param("washingMachine") WashingMachine washingMachine);
}
