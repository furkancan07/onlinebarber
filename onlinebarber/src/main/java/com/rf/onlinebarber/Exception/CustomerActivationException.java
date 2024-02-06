package com.rf.onlinebarber.Exception;

public class CustomerActivationException extends RuntimeException{

    public CustomerActivationException() {
        super("Gerekli işlemleri yapabilmek için mail adresinizi giriniz");
    }
}
