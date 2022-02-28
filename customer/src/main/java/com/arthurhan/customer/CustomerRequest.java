package com.arthurhan.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest
{
    private String firstName;
    private String lastName;
    private String Email;
}
