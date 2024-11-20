package com.learnwithak.userservice.command.api.controller;

import com.learningwithak.commonservice.model.User;
import com.learnwithak.userservice.command.api.command.CreateUserCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserCommandController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserCommandController.class);

    private CommandGateway commandGateway;

    @PostMapping
    public String addUser(@RequestBody User userRestModel) {
        LOGGER.info("Add User API is called...");
        CreateUserCommand createUserCommand =
                CreateUserCommand.builder()
                        .userId(UUID.randomUUID().toString())
                        .firstName(userRestModel.getFirstName())
                        .lastName(userRestModel.getLastName())
                        .cardDetails(userRestModel.getCardDetails())
                        .build();
        String result = commandGateway.sendAndWait(createUserCommand);
        return result;
    }
}
