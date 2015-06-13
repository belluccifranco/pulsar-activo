package com.pulsaractivo.service;

import com.pulsaractivo.model.Device;
import com.pulsaractivo.model.Event;
import com.pulsaractivo.repository.DeviceRepository;
import com.pulsaractivo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private static final int PAGE_SIZE = 2;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<Device> getDevices(Integer page) {
        PageRequest pageRequest =
                new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "name");
        return deviceRepository.findAll(pageRequest);
    }

    @Override
    public Event getDeviceLastEvent(long id) {
        Device device = deviceRepository.findOne(id);

        if (device != null) {
            List<Event> result = eventRepository.findTop2ByDevice(device, new Sort(Sort.Direction.DESC, "dateTime"));

            if (result.size() > 0) {
                return result.get(0);
            }
        }

        return null;
    }
}