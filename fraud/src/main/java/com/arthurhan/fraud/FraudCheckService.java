package com.arthurhan.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class FraudCheckService
{
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerID)
    {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerID)
                        .isFraudster(false)
                        .createdAt(Instant.now())
                        .build()
        );

        FraudCheckHistory fraudCheckHistory = fraudCheckHistoryRepository
                .findById(customerID)
                .orElseThrow(() -> new FraudException("Customer with id " + customerID + " not found."));

        return fraudCheckHistory.getIsFraudster();
    }
}
