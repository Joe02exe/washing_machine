package com.waschmaschine.demo.services;

import com.waschmaschine.demo.configs.WebSecurityConfig;
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
import java.util.Objects;

@Service
public class PersonService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    public Person findPersonByUsername(String username) throws UsernameNotFoundException{
        Person person = personRepository.findPersonByUsername(username);
        if (person == null){
            throw new UsernameNotFoundException("Username: " + username +" was not found");
        }
        return person;
    }

    public List<Person> findAllPersons(){
        return personRepository.findAll();
    }

    public Person addPerson(Person person){
        person.setRole(UserRole.USER);
        person.setPassword(WebSecurityConfig.passwordEncoder().encode(person.getPassword()));
        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        if(!Objects.equals(findPersonByUsername(person.getUsername()).getPassword(), person.getUsername())){
            person.setPassword(WebSecurityConfig.passwordEncoder().encode(person.getPassword()));
        }
        return personRepository.save(person);
    }

    public void deleteEmployeeByUsername(String username) {
        personRepository.deletePersonByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findPersonByUsername(username);
        return new CustomUserDetails(person);
    }
}
