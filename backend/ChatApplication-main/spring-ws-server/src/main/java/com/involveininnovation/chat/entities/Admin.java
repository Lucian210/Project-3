package com.involveininnovation.chat.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin extends UserAuth {
    private String anunt;

    public Admin(Long id, String username, String password, String nume, String oras, String birthdate){
        super(id, username, password, nume, oras, birthdate);
        this.setId(id);
        this.setName(nume);
        this.setAddress(oras);
        this.setUsername(username);
        this.setPassword(password);
    }

}

