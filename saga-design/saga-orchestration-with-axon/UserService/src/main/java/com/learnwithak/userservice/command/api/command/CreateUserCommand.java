package com.learnwithak.userservice.command.api.command;

import com.learningwithak.commonservice.model.CardDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateUserCommand {
    @TargetAggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private CardDetails cardDetails;
}
