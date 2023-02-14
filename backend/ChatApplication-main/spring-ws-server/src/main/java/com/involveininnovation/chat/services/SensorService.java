package com.involveininnovation.chat.services;

import org.springframework.stereotype.Component;
import com.involveininnovation.chat.dtos.NewSensorDTO;
import com.involveininnovation.chat.dtos.SensorDTO;
import com.involveininnovation.chat.entities.Sensor;
import com.involveininnovation.chat.entities.SensorInfo;

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
