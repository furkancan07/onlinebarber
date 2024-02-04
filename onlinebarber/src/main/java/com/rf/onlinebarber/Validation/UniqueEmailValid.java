package com.rf.onlinebarber.Validation;

import com.rf.onlinebarber.Repository.ShopRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValid implements ConstraintValidator<UniqueEmail,String> {
    private final ShopRepository shopRepository;

    public UniqueEmailValid(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(shopRepository.existsByEmail(s)) return false;
        return true;
    }
}
