package ro.tuc.ds2020.services;

import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.CredentialsDTO;
import ro.tuc.ds2020.dtos.LoginSuccessDTO;

import java.sql.Timestamp;

@Component
public interface LoginService {

    public LoginSuccessDTO login(CredentialsDTO dto, Timestamp ts) throws ro.tuc.ds2020.exceptions.ApiExceptionResponse;
}
