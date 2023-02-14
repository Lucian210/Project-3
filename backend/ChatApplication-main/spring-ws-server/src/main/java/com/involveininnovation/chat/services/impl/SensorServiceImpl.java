package com.involveininnovation.chat.services.impl;


import org.springframework.stereotype.Service;
import com.involveininnovation.chat.dtos.NewSensorDTO;
import com.involveininnovation.chat.dtos.SensorDTO;
import com.involveininnovation.chat.entities.Device;
import com.involveininnovation.chat.entities.Sensor;
import com.involveininnovation.chat.entities.SensorInfo;
import com.involveininnovation.chat.repositories.DeviceRepository;
import com.involveininnovation.chat.repositories.SensorRepository;
import com.involveininnovation.chat.services.SensorService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    //injectare
    private final SensorRepository sensorRepository;
    private final DeviceRepository deviceRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, DeviceRepository deviceRepository) {
        this.sensorRepository = sensorRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Sensor findSensorById(Long id) {
        return sensorRepository.findSensorById(id);
    }

    @Override
    public List<Sensor> findAll() {
        return (List<Sensor>) sensorRepository.findAll();
    }


    @Override
    @Transactional
    public Sensor updateSensor(SensorDTO sensorDTO) {
        Sensor updatedSensor = sensorRepository.findById(sensorDTO.getId()).orElseThrow();
        if(!sensorDTO.getSensorDescription().isEmpty()){
            updatedSensor.setSensorDescription(sensorDTO.getSensorDescription());
        }
        if(!sensorDTO.getMaxValue().isEmpty()){
            updatedSensor.setMaxValue(Integer.parseInt(sensorDTO.getMaxValue()));
        }

        if(deviceRepository.findDeviceById(sensorDTO.getDeviceId()) != null){
            updatedSensor.setDevice(deviceRepository.findDeviceById(sensorDTO.getDeviceId()));
        }


        return updatedSensor;
    }

    @Override
    public List<Sensor> deleteSensor(Long id){
        sensorRepository.deleteById(id);
        return sensorRepository.findAll();
    }

    @Override
    public Sensor addSensor(NewSensorDTO dto) {
        Device assignedDevice = deviceRepository.findDeviceById(dto.getDeviceId());
        //System.out.println(assignedClient.getName());
        Sensor newSensor;
        if(assignedDevice != null ){
            newSensor = new Sensor(assignedDevice, dto.getDescription(), dto.getMaxValue());
        }
        else{
            newSensor = new Sensor(dto.getDescription(), dto.getMaxValue());
        }
        sensorRepository.save(newSensor);
        return newSensor;
    }

    @Override
    public List<SensorInfo> findSensorInfoByClient(Long clientId) {
        List<Device> allDevices = deviceRepository.findAll();
        List<Device> clientDevices = new ArrayList<Device>();
        List<SensorInfo> clientSensorInfo = new ArrayList<>();
        List<Sensor> allSensors = sensorRepository.findAll();

        for (Device curr: allDevices) {
            if(curr.getClient().getId() == clientId){
                clientDevices.add(curr);
            }
        }

        for(Device clientDevice: clientDevices){
            for(Sensor currSensor: allSensors){
                if(currSensor.getDevice().getId() == clientDevice.getId()){
                    System.out.println(currSensor.getSensorInfo());
                    clientSensorInfo = currSensor.getSensorInfo();
                }
            }
        }
        //System.out.println(clientSensorInfo.get(0));
        return clientSensorInfo;
    }
}
