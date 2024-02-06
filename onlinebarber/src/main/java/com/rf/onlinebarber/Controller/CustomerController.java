package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.ActivateCustomerRequest;
import com.rf.onlinebarber.Dto.SendActivationCodeRequest;
import com.rf.onlinebarber.Dto.SaveCustomerRequest;
import com.rf.onlinebarber.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // hesabı aktif etmek için mail adresine aktivasyon kodu gönderme
    @PostMapping("/sendActivationCode")
    public ResponseEntity<?> sendActivationCode(@Valid @RequestBody SendActivationCodeRequest request){
        return customerService.sendActivationCode(request);
    }
    // hesabı aktif etme
    @PatchMapping("/active")
    public ResponseEntity<?> active(@Valid @RequestBody ActivateCustomerRequest request){
        return customerService.active(request);
    }

}
