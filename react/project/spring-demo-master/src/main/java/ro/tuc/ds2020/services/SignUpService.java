package ro.tuc.ds2020.services;


import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.NewUserDTO;
import ro.tuc.ds2020.entities.UserAuth;

@Component
public interface SignUpService {

    public UserAuth signUp(NewUserDTO dto) throws ro.tuc.ds2020.exceptions.ApiExceptionResponse;
}
