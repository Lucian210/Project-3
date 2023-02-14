package ro.tuc.ds2020.consumer;

import ro.tuc.ds2020.dtos.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(DTO message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}