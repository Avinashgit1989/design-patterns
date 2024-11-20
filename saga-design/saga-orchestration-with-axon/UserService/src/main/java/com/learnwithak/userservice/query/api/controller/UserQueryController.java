package com.learnwithak.userservice.query.api.controller;

import com.learningwithak.commonservice.model.User;
import com.learningwithak.commonservice.queries.GetUserDetailsByIdQuery;
import com.learnwithak.userservice.query.api.query.GetAllUserDetailsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserQueryController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserQueryController.class);
    private transient QueryGateway queryGateway;

    public UserQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable String userId){
        LOGGER.info("Get a User details API is called...");
        GetUserDetailsByIdQuery getUserDetailsQuery
                = new GetUserDetailsByIdQuery(userId);
        User user =
                queryGateway.query(getUserDetailsQuery,
                        ResponseTypes.instanceOf(User.class)).join();

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUserDetails(){
        LOGGER.info("Get All User details API is called...");
        GetAllUserDetailsQuery getAllUserDetailsQuery
                = new GetAllUserDetailsQuery();
        List<User> user =
                queryGateway.query(getAllUserDetailsQuery,
                        ResponseTypes.multipleInstancesOf(User.class)).join();

        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
}
