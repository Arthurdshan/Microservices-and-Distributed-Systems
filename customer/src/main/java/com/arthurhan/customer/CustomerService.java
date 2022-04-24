package com.arthurhan.customer;

import com.arthurhan.amqp.RabbitMqMessageProducer;
import com.arthurhan.clients.fraud.FraudCheckResponse;
import com.arthurhan.clients.fraud.FraudClient;
import com.arthurhan.clients.notification.NotificationClient;
import com.arthurhan.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService
{
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMqMessageProducer messageProducer;

    public CustomerResponse registerCustomer(CustomerRequest customerRequest)
    {
        Customer customer = customerRepository
                .saveAndFlush(Customer.of(customerRequest));

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        assert fraudCheckResponse != null;

        if (fraudCheckResponse.getIsFraudster())
        {
            throw new IllegalStateException("fraudster client");
        }

        NotificationRequest notificationRequest = NotificationRequest
                .builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message("Welcome to the platform")
                .build();

        messageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

        return CustomerResponse.of(customer);
    }
}
