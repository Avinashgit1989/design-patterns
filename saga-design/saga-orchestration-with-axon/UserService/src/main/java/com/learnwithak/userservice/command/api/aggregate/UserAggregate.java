package com.learnwithak.userservice.command.api.aggregate;

import com.learningwithak.commonservice.model.CardDetails;
import com.learnwithak.userservice.command.api.command.CreateUserCommand;
import com.learnwithak.userservice.command.api.events.UserCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class UserAggregate {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserAggregate.class);

    @AggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private CardDetails cardDetails;

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        LOGGER.info("UserAggregate called to CreateUserCommand with userId :{}",
                createUserCommand.getUserId());
        //You can perform all the validations
        UserCreatedEvent userCreatedEvent =
                new UserCreatedEvent();

        BeanUtils.copyProperties(createUserCommand,userCreatedEvent);

        AggregateLifecycle.apply(userCreatedEvent);
        LOGGER.info("UserAggregateLifeCycle is applied.... ");
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        this.userId = userCreatedEvent.getUserId();
        this.firstName = userCreatedEvent.getFirstName();
        this.lastName = userCreatedEvent.getLastName();
        this.cardDetails = userCreatedEvent.getCardDetails();
    }
}
