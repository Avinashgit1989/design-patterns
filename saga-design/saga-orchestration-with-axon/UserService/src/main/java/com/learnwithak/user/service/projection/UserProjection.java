package com.learnwithak.user.service.projection;

import com.learnwithak.common.service.model.CardDetails;
import com.learnwithak.common.service.model.User;
import com.learnwithak.common.service.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery getUserPaymentDetailsQuery){
        CardDetails cardDetails = CardDetails.builder()
                .cardHolderName("Avinash Tiwari")
                .cardNumber(1234567890)
                .issueMonth(02)
                .issueYear(2023)
                .expiryMonth(02)
                .expiryYear(2028)
                .cvv(199)
                .build();
        return User.builder()
                .userId(getUserPaymentDetailsQuery.getUserId())
                .firstName("Aviansh")
                .lastName("Tiwari")
                .cardDetails(cardDetails).build();
    }
}
