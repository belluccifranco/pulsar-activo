package com.pulsaractivo.repository;

import com.pulsaractivo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findById(long id);
}
