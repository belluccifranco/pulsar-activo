package com.pulsaractivo.controller;

import javax.validation.Valid;
import com.pulsaractivo.model.Device;
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

    @RequestMapping(value = "/devices/{pageNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<Device> searchAll(@PathVariable Integer pageNumber) {
        return deviceService.getDevices(pageNumber);
    }

    @RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Device findById(@PathVariable long id) {
        Device device = deviceRepository.findOne(id);
        if (device == null) {
            //throw error entity not found.
        }
        System.out.println(device);
        return device;
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Device save(@RequestBody @Valid Device device) {
        return deviceRepository.save(device);
    }
}
