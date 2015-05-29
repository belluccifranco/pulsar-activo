package com.pulsaractivo.service;

import com.pulsaractivo.model.Event;
import org.springframework.data.domain.Page;

public interface EventService {

    Page<Event> getEvents(Integer page);
}
