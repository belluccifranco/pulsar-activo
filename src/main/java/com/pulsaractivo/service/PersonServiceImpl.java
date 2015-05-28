package com.pulsaractivo.service;

import com.pulsaractivo.model.Person;
import com.pulsaractivo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Person> getPersons(Integer page) {
        PageRequest pageRequest =
                new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "name");
        return personRepository.findAll(pageRequest);
    }
}