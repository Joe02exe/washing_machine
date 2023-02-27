package com.waschmaschine.demo.services;

import com.waschmaschine.demo.model.Event;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Collection<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event findEventById(Long id){
        return eventRepository.findEventById(id);
    }

    public Collection<Event> getEventsByPerson(Person person){
        return eventRepository.findAllByPerson(person);
    }

    public int getTotalTimeWashedByPerson(Person person){
        return getEventsByPerson(person).stream().mapToInt(Event::getMinutesWashed).sum();
    }

    public Collection<Event> getNewestEvents(){
        return getAllEvents().stream()
                .filter(event -> event.getDate()
                        .isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Event::getDate))
                .collect(Collectors.toList()).subList(0,4);
    }

    public Event addEvent(Event event){
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event){
        return eventRepository.save(event);
    }

    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }

}
