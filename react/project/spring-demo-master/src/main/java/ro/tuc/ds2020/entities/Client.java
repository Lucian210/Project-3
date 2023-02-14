package ro.tuc.ds2020.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Client extends UserAuth {

    public Client(Long id, String nume, String oras, String birthdate){
        super(id, nume, oras, birthdate);
        setName(nume);
        setAddress(oras);
    }


    public Client(Long Cnp, String username, String password, String nume, String oras, String birthdate){
        super(Cnp, username, password, nume, oras, birthdate);
        this.username = username;
        this.password = password;
        this.name = nume;
        this.address = oras;
    }

}