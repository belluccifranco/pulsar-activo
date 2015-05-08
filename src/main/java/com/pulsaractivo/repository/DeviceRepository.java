package com.pulsaractivo.repository;

import com.pulsaractivo.model.Device;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "devices", path = "devices")
public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

    List<Device> findByImei(@Param("imei") String imei);
}
