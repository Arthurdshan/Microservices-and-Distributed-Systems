package com.arthurhan.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse
{
    private Integer id;
    private String firstName;
    private String lastName;
    private String Email;

    public static CustomerResponse of(Customer customer)
    {
        CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customer, customerResponse);

        return customerResponse;
    }
}
