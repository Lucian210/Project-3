package ro.tuc.ds2020.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.tuc.ds2020.entities.Device;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewSensorDTO {
    private Long deviceId;
    private String description;
    private int maxValue;
}
