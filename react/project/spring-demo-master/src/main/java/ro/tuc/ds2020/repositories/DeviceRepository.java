package ro.tuc.ds2020.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
    Device findDeviceById(Long id);
    List<Device> findAll();

}
