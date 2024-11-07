package com.learnwithak.common.service.commads;

import com.learnwithak.common.service.model.CardDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidatePaymentCommand {
    private String paymentId;
    private String orderId;
    private CardDetails cardDetails;
}
