package ro.tuc.ds2020.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class UserAuth {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    protected Long id;

    @NotEmpty
    @Size(min=3, max=30, message = "Username must be between 3 and 30")
    protected String username;

    @NotEmpty
    protected String password;

    @NotEmpty
    protected String name;

    protected String address;

    protected String birthDate;

    protected long CNP;

    protected boolean logat;

    protected Timestamp timestamp;

    public UserAuth(Long id, String nume, String oras, String birthdate){
        id = id;
        name = nume;
        address = oras;
        birthDate = birthdate;
    }

    public UserAuth(Long Cnp, String username, String password, String nume, String oras, String birthdate){
        //this.CNP = Cnp;
        this.username = username;
        this.password = password;
        this.name = nume;
        this.address = oras;
        birthDate = birthdate;
    }

}
