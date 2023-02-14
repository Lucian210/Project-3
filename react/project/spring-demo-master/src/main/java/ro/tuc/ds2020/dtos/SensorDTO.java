package ro.tuc.ds2020.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.tuc.ds2020.entities.Device;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDTO {
    private Long id;
    private Long deviceId;
    //Device device;
    private String sensorDescription;
    private String maxValue;
}
