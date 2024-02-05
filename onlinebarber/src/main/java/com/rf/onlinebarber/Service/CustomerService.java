package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Dto.SaveCustomerRequest;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Exception.CustomerNotFoundException;
import com.rf.onlinebarber.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<?> saveCustomer(SaveCustomerRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer customer=request.toCustomer(request);
        customerRepository.save(customer);
        return ResponseEntity.ok("Müşteri kayıt oldu");
    }
    public Customer findById(Long id){
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
    }
}
