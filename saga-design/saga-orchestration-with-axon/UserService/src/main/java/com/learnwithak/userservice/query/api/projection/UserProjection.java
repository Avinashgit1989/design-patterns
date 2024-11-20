package com.learnwithak.userservice.query.api.projection;

import com.learningwithak.commonservice.model.User;
import com.learningwithak.commonservice.queries.GetUserDetailsByIdQuery;
import com.learnwithak.userservice.command.api.data.UserEntity;
import com.learnwithak.userservice.command.api.data.UserRepository;
import com.learnwithak.userservice.query.api.query.GetAllUserDetailsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserProjection {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserProjection.class);
    private UserRepository userRepository;
    @QueryHandler
    public List<User> handle(GetAllUserDetailsQuery getAllUserDetailsQuery) {
        LOGGER.info("Query Handler called to get All User details...");
        return userRepository.findAll().stream()
                .map(userEntity -> User.builder()
                        .userId(userEntity.getUserId())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .cardDetails(userEntity.getCardDetails())
                        .build())
                .collect(Collectors.toList());
    }

    @QueryHandler
    public User handle(GetUserDetailsByIdQuery getUserDetailsQuery) {
        LOGGER.info("Query Handler called to get User details by Id...");
        return userRepository.findById(getUserDetailsQuery.getUserId())
                .map(userEntity -> User.builder()
                        .userId(userEntity.getUserId())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .cardDetails(userEntity.getCardDetails())
                        .build())
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
