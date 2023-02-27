package com.waschmaschine.demo.repositories;

import com.waschmaschine.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
