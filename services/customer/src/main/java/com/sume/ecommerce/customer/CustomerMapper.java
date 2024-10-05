package com.sume.ecommerce.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request == null) {
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstname(request.firstName())
                .lastname(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        if(customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getEmail(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getAddress()
        );
    }
}
