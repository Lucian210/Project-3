package com.involveininnovation.chat.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
