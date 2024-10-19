package com.sume.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(this.service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        this.service.updateCustomer(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        var t1 = new ArrayList<Customer>();
        t1.add(new Customer(
                "1",
                "tien",
                "nguyen",
                "tien.nguyen@gmail.com",
                new Address(
                        "a1",
                        "a2",
                        "a3"
                )
        ));
        Set<String> a1 = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        t1.add(new Customer(
                "2",
                "tien1",
                "nguyen1",
                "tien1.nguyen@gmail.com",
                new Address(
                        "a11",
                        "a21",
                        "a31"
                )
        ));
        Collections.sort(t1);
        var contries1 = new ArrayList<String>();
        contries1.add("1");
        contries1.add("2");
        contries1.sort(Comparator.reverseOrder());
        Collections.sort(contries1);

        return ResponseEntity.ok(this.service.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(this.service.existsById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok(this.service.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.ok().build();
    }
}
