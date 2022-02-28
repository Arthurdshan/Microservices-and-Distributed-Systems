package com.arthurhan.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse registerCustomer(CustomerRequest customerRequest)
    {
//        Customer customer =  Customer.builder()
//                .firstName(customerRequest.getFirstName())
//                .lastName(customerRequest.getLastName())
//                .email(customerRequest.getEmail())
//                .build();

        Customer customer = customerRepository.save(Customer.of(customerRequest));

        return CustomerResponse.of(customer);
    }
}
