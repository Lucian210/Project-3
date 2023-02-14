package com.involveininnovation.chat.config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public AmqpTemplate rabbit(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbit = new RabbitTemplate(connectionFactory);
        rabbit.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbit;
    }
}