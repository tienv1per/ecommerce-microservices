package com.sume.ecommerce.kafka.order;

import org.springframework.validation.annotation.Validated;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
