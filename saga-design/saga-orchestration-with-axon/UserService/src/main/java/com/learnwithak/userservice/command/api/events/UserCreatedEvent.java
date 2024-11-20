package com.learnwithak.userservice.command.api.events;

import com.learningwithak.commonservice.model.CardDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {
    private String userId;
    private String firstName;
    private String lastName;
    private CardDetails cardDetails;
}
