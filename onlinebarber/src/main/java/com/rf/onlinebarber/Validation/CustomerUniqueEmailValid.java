package com.rf.onlinebarber.Validation;

import com.rf.onlinebarber.Repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerUniqueEmailValid implements ConstraintValidator<CustomerUniqueEmail,String> {

    private final CustomerRepository customerRepository;

    public CustomerUniqueEmailValid(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(customerRepository.existsByEmail(s)) return false;
        return true;
    }
}
