package com.pulsaractivo.service;

import com.pulsaractivo.model.Dispatcher;
import org.springframework.data.domain.Page;

public interface DispatcherService {

    Page<Dispatcher> getDispatchers(Integer page);
}
