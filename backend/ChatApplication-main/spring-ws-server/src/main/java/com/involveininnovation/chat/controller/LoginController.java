package com.involveininnovation.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.involveininnovation.chat.dtos.CredentialsDTO;
import com.involveininnovation.chat.exceptions.ApiExceptionResponse;
import com.involveininnovation.chat.services.LoginService;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@CrossOrigin
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity loginReg(@RequestBody CredentialsDTO dto) throws ApiExceptionResponse {
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        System.out.println("In controller");
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(dto, ts));
    }
}
