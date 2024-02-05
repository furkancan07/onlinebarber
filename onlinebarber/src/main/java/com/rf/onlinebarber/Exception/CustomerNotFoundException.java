package com.rf.onlinebarber.Exception;

public class CustomerNotFoundException  extends RuntimeException{

    public CustomerNotFoundException(Long id) {
        super(id+" numaralı müşteri bulunamadi");
    }
}
