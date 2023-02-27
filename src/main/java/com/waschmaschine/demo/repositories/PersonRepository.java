package com.waschmaschine.demo.repositories;

import com.waschmaschine.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
    Person findPersonByUsername(String username);
}
