package com.rf.onlinebarber.Exception;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(Long id) {
        super(id+" numaralı model bulunamadi");
    }
}
