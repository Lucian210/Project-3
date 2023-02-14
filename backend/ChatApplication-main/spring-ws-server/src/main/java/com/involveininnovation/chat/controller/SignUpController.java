package com.involveininnovation.chat.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.involveininnovation.chat.dtos.NewUserDTO;
import com.involveininnovation.chat.exceptions.ApiExceptionResponse;
import com.involveininnovation.chat.services.SignUpService;

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

