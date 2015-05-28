package com.pulsaractivo.repository;

import com.pulsaractivo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);
}
