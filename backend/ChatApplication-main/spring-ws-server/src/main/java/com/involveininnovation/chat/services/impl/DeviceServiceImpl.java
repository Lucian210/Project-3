package com.involveininnovation.chat.services.impl;


import org.springframework.stereotype.Service;
import com.involveininnovation.chat.dtos.DeviceDTO;
import com.involveininnovation.chat.dtos.NewDeviceDTO;
import com.involveininnovation.chat.entities.Client;
import com.involveininnovation.chat.entities.Device;
import com.involveininnovation.chat.entities.Sensor;
import com.involveininnovation.chat.repositories.ClientRepository;
import com.involveininnovation.chat.repositories.DeviceRepository;
import com.involveininnovation.chat.repositories.SensorRepository;
import com.involveininnovation.chat.services.DeviceService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    //injectare
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;
    private final ClientRepository clientRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, SensorRepository sensorRepository, ClientRepository clientRepository) {
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Device findDeviceById(Long id) {
        return deviceRepository.findDeviceById(id);
    }

    @Override
    public List<Device> findAll() {
        return (List<Device>) deviceRepository.findAll();
    }


    @Override
    @Transactional
    public Device updateDevice(DeviceDTO dtoDevice) {
        Device updatedDevice = deviceRepository.findById(dtoDevice.getId()).orElseThrow();
        if(!dtoDevice.getDescription().isEmpty()){
            updatedDevice.setDescription(dtoDevice.getDescription());
        }
        if(!dtoDevice.getAvgEnergyCons().isEmpty()){
            updatedDevice.setAvgEnergyCons(Integer.parseInt(dtoDevice.getAvgEnergyCons()));
        }
        if(!dtoDevice.getMaxEnergyCons().isEmpty()){
            updatedDevice.setMaxEnergyCons(Integer.parseInt(dtoDevice.getMaxEnergyCons()));
        }
        if(clientRepository.findClientById(dtoDevice.getClientId()) != null){
            updatedDevice.setClient(clientRepository.findClientById(dtoDevice.getClientId()));
        }

        return updatedDevice;
    }

    @Override
    public List<Device> deleteDevice(Long id) {
        List<Sensor> allSensors = sensorRepository.findAll();
        for(int i = 0; i < allSensors.size(); i++){
            if (allSensors.get(i).getDevice().getId() == id){
                Long currentSensorId = allSensors.get(i).getId();
                sensorRepository.deleteById(currentSensorId);
            }
        }
        deviceRepository.deleteById(id);
        return deviceRepository.findAll();
    }

    @Override
    public Device addDevice(NewDeviceDTO dto) {
        Client assignedClient = clientRepository.findClientById(dto.getClientId());
        Device newDevice;
        if(assignedClient != null ){
            newDevice = new Device(assignedClient, dto.getDescription(), dto.getAvgEngCons(), dto.getMaxEngCons());
        }
        else{
            newDevice = new Device(dto.getDescription(), dto.getAvgEngCons(), dto.getMaxEngCons());
        }
        deviceRepository.save(newDevice);
        return newDevice;
    }

    @Override
    public List<Device> findDevicesByClient(Long clientId) {
        List<Device> allDevices = deviceRepository.findAll();
        List<Device> clientDevices = new ArrayList<Device>();

        for (Device curr: allDevices) {
            if(curr.getClient().getId() == clientId){
                clientDevices.add(curr);
            }
        }
        return clientDevices;
    }
}

