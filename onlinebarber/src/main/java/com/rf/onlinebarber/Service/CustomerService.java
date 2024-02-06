package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Dto.ActivateCustomerRequest;
import com.rf.onlinebarber.Dto.SendActivationCodeRequest;
import com.rf.onlinebarber.Dto.SaveCustomerRequest;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Exception.ActivationCodeException;
import com.rf.onlinebarber.Exception.CustomerNotFoundException;
import com.rf.onlinebarber.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    public ResponseEntity<?> saveCustomer(SaveCustomerRequest request) {
        Customer customer=request.toCustomer(request);
        customerRepository.save(customer);
        return ResponseEntity.ok("Müşteri kayıt oldu");
    }
    public Customer findById(Long id){
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
    }

    public ResponseEntity<?> sendActivationCode(SendActivationCodeRequest request) {
        Customer customer=customerRepository.findByEmail(request.getEmail()).orElseThrow(()->new CustomerNotFoundException(request.getEmail()));
        Random random=new Random();
        int active= random.nextInt(900000)+100000;
        customer.setActivationCode(active+"");
        customerRepository.save(customer);
        return ResponseEntity.ok("doğrulama kodu mail adresinize gönderilmiştir");
    }
    public ResponseEntity<?> active(ActivateCustomerRequest request){
        Customer customer=customerRepository.findByActivationCode(request.getActivationCode()).orElseThrow(()->new CustomerNotFoundException());
        if(!request.getActivationCode().equals(customer.getActivationCode())) throw new ActivationCodeException();
        customer.setActive(true);
        customerRepository.save(customer);
       return ResponseEntity.ok("Hesabınız aktif olmuştur");
    }
}
