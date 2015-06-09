package com.pulsaractivo.controller;

import com.pulsaractivo.model.Event;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.repository.EventRepository;
import com.pulsaractivo.repository.DeviceRepository;
import com.pulsaractivo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;


@RestController
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public EventController(EventService eventService, EventRepository eventRepository, DeviceRepository deviceRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.deviceRepository = deviceRepository;
    }

    @RequestMapping(value = "/events/report", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Event save(@RequestBody Event event) {
        Device device = deviceRepository.findByImei(event.getImei());
        try {
        event.setDevice(device);
        /*System.out.println(device);
        System.out.println(event);*/

            return eventRepository.save(event);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /*@RequestMapping(value = "/api/devices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Device> searchAll() {
        return deviceRepository.findAll();
    }

    @RequestMapping(value = "/api/devices", method = RequestMethod.GET, params={"page"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Device> searchAll(@RequestParam(value = "page", defaultValue = "1") int page) {
        return deviceService.getDevices(page);
    }

    @RequestMapping(value = "/api/devices/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Device findById(@PathVariable long id) {
        Device device = deviceRepository.findOne(id);
        if (device == null) {
            //throw error entity not found.
        }
        System.out.println(device);
        return device;
    }

    @RequestMapping(value = "/api/devices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Device save(@RequestBody @Valid Device device) {
        return deviceRepository.save(device);
    }*/
}
