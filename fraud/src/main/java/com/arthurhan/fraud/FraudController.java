package com.arthurhan.fraud;

import com.arthurhan.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class FraudController
{
    private final FraudCheckService fraudCheckService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable Integer id)
    {
        log.info("fraud check request for customer {}", id);
        Boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(id);
        FraudCheckResponse fraudCheckResponse = new FraudCheckResponse(isFraudulentCustomer);

        return new ResponseEntity<>(fraudCheckResponse, HttpStatus.OK);
    }
}
