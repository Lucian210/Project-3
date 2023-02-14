package ro.tuc.ds2020.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.CredentialsDTO;
import ro.tuc.ds2020.dtos.LoginSuccessDTO;
import ro.tuc.ds2020.entities.UserAuth;
import ro.tuc.ds2020.exceptions.ApiExceptionResponse;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.LoginService;

import java.sql.Timestamp;
import java.util.Collections;

@Service
public class LoginServiceImpl implements LoginService {


    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public LoginSuccessDTO login(CredentialsDTO dto, Timestamp ts) throws ApiExceptionResponse {
        UserAuth userAuth = userRepository.findByUsername(dto.getUsername());
        System.out.println(userAuth.getUsername());
        if(userAuth == null){
            throw ApiExceptionResponse.builder().errors(Collections.singletonList("Bad username"))
                    .message("User not found").status(HttpStatus.NOT_FOUND).build();
        }

        LoginSuccessDTO response;
        String role = userAuth.getClass().getSimpleName().toUpperCase();

        if(role.equals("CLIENT")){
            response = LoginSuccessDTO.builder().id(userAuth.getId()).role(role).build();
        }
        else{
            response = LoginSuccessDTO.builder().role(role).build();
        }

        System.out.println(role);

            /*if(encoder.matches(dto.getPassword(), userAuth.getPassword())){
                userAuth.setLogat(true);
                userAuth.setTimestamp(ts);
                userRepository.save(userAuth);
                FitnessClubApplication.nrClientilogati ++;
                return response;
            }*/

        if(dto.getPassword().equals(userAuth.getPassword()) ){
            userAuth.setLogat(true);
            userAuth.setTimestamp(ts);
            userRepository.save(userAuth);
            return response;
        }

        throw ApiExceptionResponse.builder().errors(Collections.singletonList("Bad username"))
                .message("User not found").status(HttpStatus.NOT_FOUND).build();



    }
}
