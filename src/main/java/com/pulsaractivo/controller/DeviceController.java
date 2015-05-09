package com.pulsaractivo.controller;

import com.pulsaractivo.model.Device;
import com.pulsaractivo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(value = "/devices/{pageNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<Device> searchAll(@PathVariable Integer pageNumber) {
        return deviceService.getDevices(pageNumber);
    }
}
