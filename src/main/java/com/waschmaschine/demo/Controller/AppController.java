package com.waschmaschine.demo.Controller;

import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private PersonService personService;
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("person", new Person());
        return "register";
    }
    @PostMapping("process_register")
    public String processRegistration(Person person){
        personService.addPerson(person);
        return "register_success";
    }

    @GetMapping("list_users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUserList(Model model){
        List<Person> persons = personService.findAllPersons();
        model.addAttribute("listPersons", persons);
        model.addAttribute("loggedInUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "users";
    }
}
