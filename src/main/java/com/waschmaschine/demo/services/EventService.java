package com.waschmaschine.demo.services;

import com.waschmaschine.demo.model.Event;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event findEventById(Long id){
        return eventRepository.findEventById(id);
    }

    public List<Event> getEventsByPerson(Person person){
        return eventRepository.findAllByPerson(person).stream().toList();
    }

    public int getTotalTimeWashedByPerson(Person person){
        return getEventsByPerson(person).stream().mapToInt(Event::getMinutesWashed).sum();
    }

    public List<Event> getNewestEvents(){
        List<Event> events =  getAllEvents().stream()
                .filter(event -> event.getDate()
                        .isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Event::getDate))
                .toList();
        if (events.size() <=5){
            return events;
        }
        return events.subList(0,4);
    }

    public Event addEvent(Event event){
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event){
        return eventRepository.save(event);
    }

    public void deleteEventById(Long id){
        eventRepository.deleteEventById(id);
    }

}
