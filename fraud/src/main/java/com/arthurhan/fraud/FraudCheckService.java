package com.arthurhan.fraud;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class FraudCheckService
{
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Autowired
    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository)
    {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerID)
    {
        FraudCheckHistory fraudCheckHistory = fraudCheckHistoryRepository
                .findById(customerID)
                .orElseThrow(() -> new FraudException("Customer with id " + customerID + " not found."));

        return fraudCheckHistory.getIsFraudster();
    }
}
