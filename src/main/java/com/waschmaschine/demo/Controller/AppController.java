package com.waschmaschine.demo.Controller;

import com.waschmaschine.demo.model.Event;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.model.WashingMachine;
import com.waschmaschine.demo.services.EventService;
import com.waschmaschine.demo.services.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("")
public class AppController {
    @Autowired
    private PersonService personService;

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeNowExactly = timeNow.minusMinutes(timeNow.getMinute()%30);
        String machineOneThirtyMinutes = eventService.getEventString(timeNowExactly.plusMinutes(30), WashingMachine.ONE);
        String machineOneSixtyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(60), WashingMachine.ONE);
        String machineOneNinetyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(90), WashingMachine.ONE);
        String machineOneHundredTwentyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(120), WashingMachine.ONE);
        String machineOneHundredFiftyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(150), WashingMachine.ONE);
        String machineTwoThirtyMinutes = eventService.getEventString(timeNowExactly.plusMinutes(30), WashingMachine.TWO);
        String machineTwoSixtyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(60), WashingMachine.TWO);
        String machineTwoNinetyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(90), WashingMachine.TWO);
        String machineTwoHundredTwentyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(120), WashingMachine.TWO);
        String machineTwoHundredFiftyMinutes =  eventService.getEventString(timeNowExactly.plusMinutes(150), WashingMachine.TWO);

        model.addAttribute("machineOneThirtyMinutes",machineOneThirtyMinutes);
        model.addAttribute("machineOneSixtyMinutes", machineOneSixtyMinutes);
        model.addAttribute("machineOneNinetyMinutes", machineOneNinetyMinutes);
        model.addAttribute("machineOneHundredTwentyMinutes", machineOneHundredTwentyMinutes);
        model.addAttribute("machineOneHundredFiftyMinutes", machineOneHundredFiftyMinutes);
        model.addAttribute("machineTwoThirtyMinutes", machineTwoThirtyMinutes);
        model.addAttribute("machineTwoSixtyMinutes", machineTwoSixtyMinutes);
        model.addAttribute("machineTwoNinetyMinutes", machineTwoNinetyMinutes);
        model.addAttribute("machineTwoHundredTwentyMinutes", machineTwoHundredTwentyMinutes);
        model.addAttribute("machineTwoHundredFiftyMinutes", machineTwoHundredFiftyMinutes);

        LocalTime now = LocalTime.of(timeNowExactly.getHour(), timeNowExactly.getMinute());
        String timeThirty = now.plusMinutes(30).toString();
        String timeSixty= now.plusMinutes(60).toString();
        String timeNinety = now.plusMinutes(90).toString();
        String timeHundredTwenty = now.plusMinutes(120).toString();
        String timeHundredFifty = now.plusMinutes(150).toString();

        model.addAttribute("timeThirty", timeThirty);
        model.addAttribute("timeSixty", timeSixty);
        model.addAttribute("timeNinety", timeNinety);
        model.addAttribute("timeHundredTwenty", timeHundredTwenty);
        model.addAttribute("timeHundredFifty", timeHundredFifty);

        Event event1 = eventService.getEventWithinTime(timeNowExactly, WashingMachine.ONE);
        String machineOneNow = event1 == null ? "Waschmaschine 1 ist gerade frei" : event1.getPerson().getUsername() + "(" + event1.getPerson().getFloor() +")" + " wäscht gerade";
        Event event2 = eventService.getEventWithinTime(timeNowExactly, WashingMachine.TWO);
        String machineTwoNow = event2 == null ? "Waschmaschine 2 ist gerade frei" : event2.getPerson().getUsername() + "(" + event2.getPerson().getFloor() +")" + " wäscht gerade";
        model.addAttribute("machineTwoNow", machineTwoNow);
        model.addAttribute("machineOneNow", machineOneNow);
        model.addAttribute("isAuthenticated", !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser"));
        model.addAttribute("loggedUser", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("isAdmin", SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));

        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }
    @PostMapping("/process_register")
    public String processRegistration(HttpServletRequest request, Person person, Model model){
        if(personService.findPersonByUsername(person.getUsername())!=null){
            model.addAttribute("person", new Person());
            return "registerFail";
        }
        personService.addPerson(person);

        return "registerSuccess";
    }

    @GetMapping("/list_users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUserList(Model model){
        List<Person> persons = personService.findAllPersons();
        model.addAttribute("listPersons", persons);
        model.addAttribute("loggedInUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "users";
    }
}
