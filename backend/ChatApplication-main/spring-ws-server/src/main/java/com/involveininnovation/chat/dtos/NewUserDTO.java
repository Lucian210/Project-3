package com.involveininnovation.chat.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;

}
