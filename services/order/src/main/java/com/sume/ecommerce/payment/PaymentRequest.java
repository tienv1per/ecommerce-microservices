package com.sume.ecommerce.payment;

import com.sume.ecommerce.customer.CustomerResponse;
import com.sume.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
