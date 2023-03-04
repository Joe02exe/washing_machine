package com.waschmaschine.demo.Controller;

import com.waschmaschine.demo.configs.WebSecurityConfig;
import com.waschmaschine.demo.model.Floor;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.model.UserRole;
import com.waschmaschine.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private PersonService personService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/guest")
    public String guestLogin(Model model){
        model.addAttribute("person", new Person());
        return "guestLogin";
    }

    @PostMapping("/process_guest")
    public String processRegistration(Person person, Model model){
        person.setRole(UserRole.GUEST);
        person.setPassword(WebSecurityConfig
                .passwordEncoder()
                .encode(String
                        .valueOf(((int)(Math.floor(Math.random()*100000))))));
        if(personService.findPersonByUsername(person.getUsername())!=null){
            model.addAttribute("person", new Person());
            return "registerFail";
        }
        System.out.println("Found2222!!!!!!");

        personService.addPerson(person);
        return "guestLoginSuccess";
    }
}
