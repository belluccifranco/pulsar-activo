package com.pulsaractivo.repository;

import com.pulsaractivo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findById(long id);
}
