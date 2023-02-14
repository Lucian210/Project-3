package com.involveininnovation.chat.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.involveininnovation.chat.dtos.ClientDTO;
import com.involveininnovation.chat.services.ClientService;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    //injectam serviceul
    private final ClientService clientService;
    //private final JavaMailSender javaMailSender;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        //this.javaMailSender = javaMailSender;
    }

    @ApiOperation(value = "Return a list of all clients")
    @GetMapping()
    public ResponseEntity findAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity findClientById(@ApiParam(value = "Requires an id") @PathVariable Long id) throws com.involveininnovation.chat.exceptions.ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity logoutClient(@ApiParam(value = "Requires an id") @PathVariable Long id) throws com.involveininnovation.chat.exceptions.ApiExceptionResponse{
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.logoutClient(id, ts));
    }


    @PutMapping()
    public ResponseEntity updateClientInfo(@RequestBody ClientDTO dto)throws com.involveininnovation.chat.exceptions.ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(dto));
    }

    @PutMapping("/delete/{cnp}")
    public ResponseEntity deleteClientInfo(@PathVariable Long cnp)throws com.involveininnovation.chat.exceptions.ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.deleteClient(cnp));
    }


}
