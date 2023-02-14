package ro.tuc.ds2020.services;

import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.NewSensorDTO;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SensorInfo;

import java.util.List;

@Component
public interface SensorService {
    Sensor findSensorById(Long id);
    List<Sensor> findAll();
    Sensor updateSensor(SensorDTO sensorDTO);
    List<Sensor> deleteSensor(Long id);
    Sensor addSensor(NewSensorDTO dto);
    List<SensorInfo> findSensorInfoByClient(Long clientId);

}
