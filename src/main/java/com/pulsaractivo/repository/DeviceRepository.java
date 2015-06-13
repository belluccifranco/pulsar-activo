package com.pulsaractivo.repository;

import java.util.List;
import com.pulsaractivo.model.Device;
import com.pulsaractivo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAll();
    Device findByImei(/*@Param("imei") */String imei);
}
