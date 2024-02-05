package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Validation.CustomerUniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveCustomerRequest {
    @CustomerUniqueEmail
    @Email
    private String email;
    @NotNull
    private String phoneNumber;

    public Customer toCustomer(SaveCustomerRequest request){
        return Customer.builder().
        email(request.getEmail()).phoneNumber(request.getPhoneNumber()).
                build();
    }
}
