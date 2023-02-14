package com.involveininnovation.chat.consumer;

import com.involveininnovation.chat.dtos.SensorDTO;
import com.involveininnovation.chat.services.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.involveininnovation.chat.dtos.DTO;

@Service
public class RabbitMQConsumer {

    private final SensorService sensorService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    public RabbitMQConsumer(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(DTO message){
        LOGGER.info(String.format("Received message -> %s", message));
        SensorDTO sensor = new SensorDTO(message.getSensorId(), message.getSensorId(), "", Integer.toString(message.getConsumption().intValue()));
        sensorService.updateSensor(sensor);
    }
}