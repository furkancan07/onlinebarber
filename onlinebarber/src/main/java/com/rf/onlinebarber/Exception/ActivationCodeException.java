package com.rf.onlinebarber.Exception;

public class ActivationCodeException extends RuntimeException {
    public ActivationCodeException() {
        super("Girdiğiniz aktivasyon kodu yanlış");
    }
}
