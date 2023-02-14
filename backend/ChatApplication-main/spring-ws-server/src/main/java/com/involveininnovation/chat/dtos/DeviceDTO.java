package com.involveininnovation.chat.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDTO {
    private Long id;
    private Long clientId;
    private String description;
    private String maxEnergyCons;
    private String avgEnergyCons;
}
