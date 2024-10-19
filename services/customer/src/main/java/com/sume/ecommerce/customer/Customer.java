package com.sume.ecommerce.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Customer implements Comparable<Customer> {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

    @Override
    public int compareTo(Customer o) {
        return 0;
    }
}
