package com.rf.onlinebarber.ErrorManagament;

import com.rf.onlinebarber.Exception.CustomerNotFoundException;
import com.rf.onlinebarber.Exception.ModelNotFoundException;
import com.rf.onlinebarber.Exception.ShopNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    // validayonlardan gelen hataların yönetimi
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validationException(MethodArgumentNotValidException ex ,HttpServletRequest http){
        ApiError apiError=new ApiError();
        Map<String,String> errors=new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        apiError=ApiError.builder().status(400).timestamp(apiError.getTimestamp()).
        errors(errors).path(http.getRequestURI()).
                message("error").
                build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ApiError> ShopNotFoundEx(ShopNotFoundException ex,HttpServletRequest http){
        ApiError apiError=new ApiError();
        apiError=ApiError.builder().path(http.getRequestURI()).status(404).timestamp(apiError.getTimestamp()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ApiError> ModelNotFoundEx(ModelNotFoundException ex,HttpServletRequest http){
        ApiError apiError=new ApiError();
        apiError=ApiError.builder().path(http.getRequestURI()).status(404).timestamp(apiError.getTimestamp()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiError> CustomerNotFoundEx(CustomerNotFoundException ex,HttpServletRequest http){
        ApiError apiError=new ApiError();
        apiError=ApiError.builder().path(http.getRequestURI()).status(404).timestamp(apiError.getTimestamp()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
