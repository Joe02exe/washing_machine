package com.waschmaschine.demo.Controller;

import com.waschmaschine.demo.model.Event;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<List<Event>> getNewestEvents(){
        List<Event> events = eventService.getNewestEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/all/{person}")
    public ResponseEntity<List<Event>> getEventsByPerson(@PathVariable("person") Person person){
        List<Event> events = eventService.getEventsByPerson(person);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/timeWashed/{person}")
    public ResponseEntity<Integer> getTimeWashedByPerson(@PathVariable("person") Person person){
        int timeWashed = eventService.getTotalTimeWashedByPerson(person);
        return new ResponseEntity<>(timeWashed, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        Event event = eventService.findEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event event1 = eventService.addEvent(event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        Event updateEvent = eventService.updateEvent(event);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
