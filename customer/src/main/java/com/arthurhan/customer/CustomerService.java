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
        Customer customer = customerRepository.saveAndFlush(Customer.of(customerRequest));

        return CustomerResponse.of(customer);
    }
}
