package org.springframework.amqp.tutorials.tut5.controller;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.tutorials.tut5.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Profile("message_controller")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    @PostMapping
    public String sendMessage(@RequestParam String routingKey, @RequestBody MessageRequest messageRequest) {
        String message = messageRequest.getMessage();
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
        return "Message sent to topic '" + topicExchange.getName() + "' with routing key '" + routingKey + "': " + message;
    }
}