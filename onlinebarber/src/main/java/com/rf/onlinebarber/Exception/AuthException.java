package com.rf.onlinebarber.Exception;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Şifre Hatalı Lütfen tekrar dene");
    }
}
