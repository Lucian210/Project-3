package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Sensor;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {
    Sensor findSensorById(Long id);
    List<Sensor> findAll();

}
