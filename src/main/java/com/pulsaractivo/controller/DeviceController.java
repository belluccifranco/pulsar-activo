package com.pulsaractivo.controller;

import java.util.List;
import javax.validation.Valid;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.model.Event;
import com.pulsaractivo.service.DeviceService;
import com.pulsaractivo.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository) {
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
    }


    @RequestMapping(value = "/api/devices/{id}/events/last", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Event findDeviceLastEvent(@PathVariable long id) {
        /*Device device = deviceRepository.findOne(id);
        if (device == null) {
            //throw error entity not found.
        }
        System.out.println(device);*/
        Event event = deviceService.getDeviceLastEvent(id);

        return event;
    }

    @RequestMapping(value = "/api/devices", method = RequestMethod.GET)
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
    }


}
