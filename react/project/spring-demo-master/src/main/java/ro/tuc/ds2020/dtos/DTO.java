package ro.tuc.ds2020.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DTO {
    Date timestamp;
    Double consumption;
    Long sensorId;
}