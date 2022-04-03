package com.arthurhan.customer;

import com.arthurhan.clients.fraud.FraudCheckResponse;
import com.arthurhan.clients.fraud.FraudClient;
import com.arthurhan.clients.notification.NotificationClient;
import com.arthurhan.clients.notification.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient)
    {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
    }

    public CustomerResponse registerCustomer(CustomerRequest customerRequest)
    {
        Customer customer = customerRepository
                .saveAndFlush(Customer.of(customerRequest));

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        assert fraudCheckResponse != null;

        if (fraudCheckResponse.getIsFraudster())
        {
            throw new IllegalStateException("fraudster");
        }

        notificationClient.sendNotification(NotificationRequest
                .builder()
                        .toCustomerId(customer.getId())
                        .toCustomerEmail(customer.getEmail())
                        .message("Welcome to the platform")
                .build());

        return CustomerResponse.of(customer);
    }
}
