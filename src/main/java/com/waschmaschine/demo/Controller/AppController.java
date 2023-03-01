package com.waschmaschine.demo.Controller;

import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.model.UserRole;
import com.waschmaschine.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private PersonService personService;
    @GetMapping("")
    public String viewHomePage(){
        return "index.html";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("person", new Person());
        return "register.html";
    }
    @PostMapping("process_register")
    public String processRegistration(Person person){
        person.setRole(UserRole.USER);
        personService.addPerson(person);
        return "register_success.html";
    }
}
