package ro.tuc.ds2020.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ClientDTO {
    private Long id;
    private String name;
    private String address;
    private String birthDate;
}
