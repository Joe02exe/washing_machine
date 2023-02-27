package com.waschmaschine.demo.services;

import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person findPersonByUsername(String username){
        return personRepository.findPersonByUsername(username);
    }

    public Collection<Person> findAllPersons(){
        return personRepository.findAll();
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }
}
