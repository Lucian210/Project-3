package com.Project2.m1.controller;

import com.Project2.m1.DTO.DTO;
import com.Project2.m1.producer.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    // http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("sensorId") long sensorId){
        try{
            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("sensor.csv"));
            while((row = csvReader.readLine()) != null){
                DTO sensorDTO = new DTO(new Date(), Double.parseDouble(row), sensorId);
               this.producer.sendMessage(sensorDTO);
                Thread.sleep(10000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Message sent");
    }
}