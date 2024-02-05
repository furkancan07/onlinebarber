package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.SaveCustomerRequest;
import com.rf.onlinebarber.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    // kayıt olma
    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody SaveCustomerRequest request){
        return customerService.saveCustomer(request);
    }
}
