package com.pulsaractivo.service;

import com.pulsaractivo.model.Person;
import org.springframework.data.domain.Page;

public interface PersonService {

    Page<Person> getPersons(Integer page);
}
