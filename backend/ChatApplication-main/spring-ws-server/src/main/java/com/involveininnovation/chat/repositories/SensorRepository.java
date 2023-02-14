package com.involveininnovation.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.involveininnovation.chat.entities.Sensor;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {
    Sensor findSensorById(Long id);
    List<Sensor> findAll();

}
