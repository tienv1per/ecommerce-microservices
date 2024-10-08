package com.sume.ecommerce.kafka;

import com.sume.ecommerce.customer.CustomerResponse;
import com.sume.ecommerce.order.PaymentMethod;
import com.sume.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
