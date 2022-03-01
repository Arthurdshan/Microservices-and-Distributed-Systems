package com.arthurhan.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public class FraudController
{
    private final FraudCheckService fraudCheckService;

    @Autowired
    public FraudController(FraudCheckService fraudCheckService)
    {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable Integer id)
    {
        log.info("fraud check request for customer {}", id);
        Boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(id);
        FraudCheckResponse fraudCheckResponse = new FraudCheckResponse(isFraudulentCustomer);

        return new ResponseEntity<>(fraudCheckResponse, HttpStatus.OK);
    }
}
