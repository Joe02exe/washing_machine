package com.waschmaschine.demo.Controller;

import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> persons = personService.findAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Person> getPersonByUsername(@PathVariable("username") String username){
        Person person = personService.findPersonByUsername(username);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person person1 = personService.addPerson(person);
        return new ResponseEntity<>(person1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Person> updateEmployee(@RequestBody Person person) {
        Person updatePerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatePerson, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("username") String username) {
        personService.deleteEmployeeByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
