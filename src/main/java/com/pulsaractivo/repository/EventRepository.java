package com.pulsaractivo.repository;

import java.util.List;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Sort;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findById(long id);

    List<Event> findTop2ByDevice(Device device, Sort sort);
}
