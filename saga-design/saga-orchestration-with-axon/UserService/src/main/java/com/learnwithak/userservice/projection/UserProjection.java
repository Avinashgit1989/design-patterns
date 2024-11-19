package com.learnwithak.userservice.projection;

import com.learningwithak.commonservice.model.CardDetails;
import com.learningwithak.commonservice.model.User;
import com.learningwithak.commonservice.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        //Ideally Get the details from the DB
        CardDetails cardDetails
                = CardDetails.builder()
                .name("Avinash Tiwari")
                .validUntilYear(2022)
                .validUntilMonth(01)
                .cardNumber("123456789")
                .cvv(111)
                .build();

        return User.builder()
                .userId(query.getUserId())
                .firstName("Avinash")
                .lastName("Tiwari")
                .cardDetails(cardDetails)
                .build();
    }
}
