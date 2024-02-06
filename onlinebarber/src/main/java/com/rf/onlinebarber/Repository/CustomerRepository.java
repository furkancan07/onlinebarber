package com.rf.onlinebarber.Repository;

import com.rf.onlinebarber.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public boolean existsByEmail(String email);
    public Optional <Customer> findByEmail(String email);
    public Optional<Customer> findByActivationCode(String activationCode);
    public List<Customer> findByActiveTrue();
}
