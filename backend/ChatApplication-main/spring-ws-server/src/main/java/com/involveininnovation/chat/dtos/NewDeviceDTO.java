package com.involveininnovation.chat.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewDeviceDTO {
    private Long clientId;
    private String description;
    private int avgEngCons;
    private int maxEngCons;
}
