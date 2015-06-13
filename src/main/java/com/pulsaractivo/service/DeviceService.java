package com.pulsaractivo.service;

import com.pulsaractivo.model.Device;
import com.pulsaractivo.model.Event;
import org.springframework.data.domain.Page;

public interface DeviceService {

    Page<Device> getDevices(Integer page);
    Event getDeviceLastEvent(long id);
}
