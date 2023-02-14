package ro.tuc.ds2020.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
@XmlRootElement(name = "subscription")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Device {

    public Device(Client client, String description, int maxEnergyCons, int avgEnergyCons){
        this.client = client;
        this.description = description;
        this.maxEnergyCons = maxEnergyCons;
        this.avgEnergyCons = avgEnergyCons;

    }
    public Device(String description, int maxEnergyCons, int avgEnergyCons){
        this.description = description;
        this.maxEnergyCons = maxEnergyCons;
        this.avgEnergyCons = avgEnergyCons;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @OneToOne
    Client client;

    private String description;

    private int maxEnergyCons;

    private int avgEnergyCons;

}
