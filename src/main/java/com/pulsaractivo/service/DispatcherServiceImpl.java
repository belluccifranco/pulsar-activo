package com.pulsaractivo.service;

import com.pulsaractivo.model.Dispatcher;
import com.pulsaractivo.repository.DispatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DispatcherServiceImpl implements DispatcherService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private DispatcherRepository dispatcherRepository;

    @Override
    public Page<Dispatcher> getDispatchers(Integer page) {
        PageRequest pageRequest =
                new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
        return dispatcherRepository.findAll(pageRequest);
    }
}