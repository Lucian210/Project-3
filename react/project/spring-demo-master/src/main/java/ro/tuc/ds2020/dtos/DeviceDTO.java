package ro.tuc.ds2020.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.tuc.ds2020.entities.Client;

import javax.persistence.OneToOne;
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
