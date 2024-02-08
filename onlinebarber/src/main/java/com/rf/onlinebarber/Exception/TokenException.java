package com.rf.onlinebarber.Exception;

public class TokenException extends RuntimeException {
    public TokenException() {
        super("Doğrulama Hatası");
    }
}
