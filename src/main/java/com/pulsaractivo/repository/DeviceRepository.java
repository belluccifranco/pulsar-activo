package com.pulsaractivo.repository;

import java.util.List;
import com.pulsaractivo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAll();

    @Query("select d from Device d where d.imei = :imei")
    Device findByImei(@Param("imei") String imei);

}
