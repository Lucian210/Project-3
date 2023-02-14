package ro.tuc.ds2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class SensorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public SensorInfo(String timeStamp, int cons){
        this.timeStamp = timeStamp;
        this.cons = cons;
    }
    private String timeStamp;
    private int cons;

}
