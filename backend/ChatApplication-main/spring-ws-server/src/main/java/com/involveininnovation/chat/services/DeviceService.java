package com.involveininnovation.chat.services;

import org.springframework.stereotype.Component;
import com.involveininnovation.chat.dtos.DeviceDTO;
import com.involveininnovation.chat.dtos.NewDeviceDTO;
import com.involveininnovation.chat.entities.Device;

import java.util.List;

@Component
public interface DeviceService {
    Device findDeviceById(Long id);
    List<Device> findAll();
    Device updateDevice(DeviceDTO dto);
    List<Device> deleteDevice(Long id);
    Device addDevice(NewDeviceDTO dto);
    List<Device> findDevicesByClient(Long clientId);

}
