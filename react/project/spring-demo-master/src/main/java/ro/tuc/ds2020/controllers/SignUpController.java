package ro.tuc.ds2020.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.NewUserDTO;
import ro.tuc.ds2020.exceptions.ApiExceptionResponse;
import ro.tuc.ds2020.services.SignUpService;

@RestController
@CrossOrigin
public class SignUpController {
    private final SignUpService signUpService;


    public SignUpController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @PostMapping("/newUser")
    public ResponseEntity signUp(@RequestBody NewUserDTO dto) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(signUpService.signUp(dto));
    }
}

