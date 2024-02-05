package com.rf.onlinebarber.Repository;

import com.rf.onlinebarber.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public boolean existsByEmail(String email);
}
