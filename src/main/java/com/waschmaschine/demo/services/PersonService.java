package com.waschmaschine.demo.services;

import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person findPersonByUsername(String username){
        return personRepository.findPersonByUsername(username);
    }

    public List<Person> findAllPersons(){
        return personRepository.findAll();
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public void deleteEmployeeByUsername(String username) {
        personRepository.deletePersonByUsername(username);
    }
}
