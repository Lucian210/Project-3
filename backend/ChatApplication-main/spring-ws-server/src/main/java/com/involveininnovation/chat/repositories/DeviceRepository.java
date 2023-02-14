package com.involveininnovation.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.involveininnovation.chat.entities.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
    Device findDeviceById(Long id);
    List<Device> findAll();

}
