package com.pulsaractivo.repository;

import com.pulsaractivo.model.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {

    Dispatcher findById(long id);
}
