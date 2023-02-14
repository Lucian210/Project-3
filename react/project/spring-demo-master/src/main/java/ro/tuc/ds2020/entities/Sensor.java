package ro.tuc.ds2020.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Sensor {

    public Sensor(Device device, String description, int maxValue){
        this.device = device;
        this.sensorDescription = description;
        this.maxValue = maxValue;
    }
    public Sensor(Device device, String description, int maxValue, List<SensorInfo> sensorInfo){
        this.device = device;
        this.sensorDescription = description;
        this.maxValue = maxValue;
        this.sensorInfo = sensorInfo;
    }
    public Sensor( String description, int maxValue){
        this.sensorDescription = description;
        this.maxValue = maxValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @OneToOne
    Device device;

    private String sensorDescription;

    private int maxValue;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(value = CascadeType.ALL)
    List<SensorInfo> sensorInfo;

}
