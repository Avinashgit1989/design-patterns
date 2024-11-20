package com.learnwithak.userservice.command.api.events;

import com.learnwithak.userservice.command.api.data.UserEntity;
import com.learnwithak.userservice.command.api.data.UserRepository;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("user")
@AllArgsConstructor
public class UsersEventHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(UsersEventHandler.class);

    private UserRepository userRepository;

    @EventHandler
    public void on(UserCreatedEvent event) throws Exception {
        LOGGER.info("UserCreateEvent handler called...");
        UserEntity userEntity =
                new UserEntity();
        BeanUtils.copyProperties(event,userEntity);
       UserEntity userResponse = userRepository.save(userEntity);
       if (userResponse == null) {
           throw new Exception("Exception Occurred while adding user details");
       }
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}

