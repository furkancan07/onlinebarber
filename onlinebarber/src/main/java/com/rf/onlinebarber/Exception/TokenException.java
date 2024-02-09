package com.rf.onlinebarber.Exception;

public class TokenException extends RuntimeException {
    public TokenException() {
        super("Token artık geçersiz çıkış yap");
    }
}
