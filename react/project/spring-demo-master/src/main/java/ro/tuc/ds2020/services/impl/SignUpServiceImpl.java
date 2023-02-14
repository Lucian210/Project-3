package ro.tuc.ds2020.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.NewUserDTO;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.UserAuth;
import ro.tuc.ds2020.exceptions.ApiExceptionResponse;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.SignUpService;

import java.util.Collections;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    //private PasswordEncoder encoder;
    private final UserRepository userRepository;
    public SignUpServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserAuth signUp(NewUserDTO dto) throws ApiExceptionResponse {
        //UserAuth newUser = new Client(null, dto.getUsername(), encoder.encode(dto.getPassword()), dto.getFirstName()+ " " + dto.getLastName(), dto.getAddress());
        System.out.println(dto.getAddress());
        UserAuth newUser = new Client(null, dto.getUsername(), dto.getPassword(), dto.getFirstName()+ " " + dto.getLastName(), dto.getAddress(), dto.getBirthDate());
        Client oldClient = (Client) userRepository.findByUsername(newUser.getUsername());

        if(oldClient != null){
            System.out.println("Acest username e deja folosit");
            throw ApiExceptionResponse.builder().errors(Collections.singletonList("Bad username"))
                    .message("User not found").status(HttpStatus.NOT_FOUND).build();
        }

        userRepository.save(newUser);
        return newUser;

    }
}
