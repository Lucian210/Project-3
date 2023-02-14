package ro.tuc.ds2020.services;

import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.NewDeviceDTO;
import ro.tuc.ds2020.entities.Device;

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
