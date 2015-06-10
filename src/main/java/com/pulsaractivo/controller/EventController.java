package com.pulsaractivo.controller;

import com.pulsaractivo.model.Event;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.repository.EventRepository;
import com.pulsaractivo.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class EventController {

    private final EventRepository eventRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public EventController(EventRepository eventRepository, DeviceRepository deviceRepository) {
        this.eventRepository = eventRepository;
        this.deviceRepository = deviceRepository;
    }

    @RequestMapping(value = "/events/report", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Event save(@RequestBody Event event) {
        Device device = deviceRepository.findByImei(event.getImei());
        //Todo: check if device is null (trow an error
        event.setDevice(device);
        return eventRepository.save(event);
    }
}
