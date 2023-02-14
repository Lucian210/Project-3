package com.involveininnovation.chat.services;

import org.springframework.stereotype.Component;
import com.involveininnovation.chat.dtos.CredentialsDTO;
import com.involveininnovation.chat.dtos.LoginSuccessDTO;

import java.sql.Timestamp;

@Component
public interface LoginService {

    public LoginSuccessDTO login(CredentialsDTO dto, Timestamp ts) throws com.involveininnovation.chat.exceptions.ApiExceptionResponse;
}
