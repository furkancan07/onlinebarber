package com.rf.onlinebarber.Exception;

public class CustomerNotFoundException  extends RuntimeException{

    public CustomerNotFoundException(Long id) {
        super(id+" numaralı müşteri bulunamadi");
    }

    public CustomerNotFoundException() {
        super("müşteri bulunamadi");
    }

    public CustomerNotFoundException(String email) {
        super(email+  " adresine sahip müşteri bulunamadi");
    }
}
