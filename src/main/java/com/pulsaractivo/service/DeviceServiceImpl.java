package com.pulsaractivo.service;

import com.pulsaractivo.model.Device;
import com.pulsaractivo.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Page<Device> getDevices(Integer pageNumber) {
        PageRequest pageRequest =
                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "name");
        return deviceRepository.findAll(pageRequest);
    }
}