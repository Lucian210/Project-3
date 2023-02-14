package com.involveininnovation.chat.services;


import org.springframework.stereotype.Component;
import com.involveininnovation.chat.dtos.NewUserDTO;
import com.involveininnovation.chat.entities.UserAuth;

@Component
public interface SignUpService {

    public UserAuth signUp(NewUserDTO dto) throws com.involveininnovation.chat.exceptions.ApiExceptionResponse;
}
