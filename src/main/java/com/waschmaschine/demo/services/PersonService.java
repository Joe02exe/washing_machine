package com.waschmaschine.demo.services;

import com.waschmaschine.demo.Controller.CustomUserDetails;
import com.waschmaschine.demo.model.Person;
import com.waschmaschine.demo.model.UserRole;
import com.waschmaschine.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    public Person findPersonByUsername(String username) throws UsernameNotFoundException{
        return personRepository.findPersonByUsername(username);
    }

    public List<Person> findAllPersons(){
        return personRepository.findAll();
    }

    public Person addPerson(Person person){
        person.setRole(UserRole.USER);
        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public void deleteEmployeeByUsername(String username) {
        personRepository.deletePersonByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findPersonByUsername(username);
        if (person == null) {throw new UsernameNotFoundException("Username was not found");}
        return new CustomUserDetails(person);
    }

    public String generatePassword(){
        int random = (int)(Math.random()*1000000000);
        return String.valueOf(random);
    }
}
