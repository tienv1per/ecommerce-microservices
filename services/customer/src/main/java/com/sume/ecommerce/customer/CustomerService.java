package com.sume.ecommerce.customer;

import com.sume.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request){
        var customer = this.repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request){
        var customer = this.repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update customer:: No customer found with provided ID:: %s", request.id())));
        mergeCustomer(customer, request);
        this.repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request){
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstname(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastname(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers(){
        return this.repository.findAll().stream().map(this.mapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String Id){
        return this.repository.findById(Id).isPresent();
    }

    public CustomerResponse findById(String Id){
        return this.repository.findById(Id)
            .map(this.mapper::fromCustomer)
            .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", Id)));
    }

    public void deleteCustomer(String Id){
        this.repository.deleteById(Id);
    }
}
