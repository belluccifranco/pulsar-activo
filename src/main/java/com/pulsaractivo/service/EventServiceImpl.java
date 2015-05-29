package com.pulsaractivo.service;

import com.pulsaractivo.model.Event;
import com.pulsaractivo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<Event> getEvents(Integer page) {
        PageRequest pageRequest =
                new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "dateTime");
        return eventRepository.findAll(pageRequest);
    }
}