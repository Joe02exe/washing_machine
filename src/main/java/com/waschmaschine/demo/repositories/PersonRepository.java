package com.waschmaschine.demo.repositories;

import com.waschmaschine.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PersonRepository extends JpaRepository<Person, String> {
    Person findPersonByUsername(String username);

    void deletePersonByUsername(String username);
}
