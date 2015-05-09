package com.pulsaractivo.service;

import com.pulsaractivo.model.Device;
import org.springframework.data.domain.Page;

public interface DeviceService {

    Page<Device> getDevices(Integer pageNumber);
}
