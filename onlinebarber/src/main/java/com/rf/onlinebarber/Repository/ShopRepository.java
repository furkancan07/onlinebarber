package com.rf.onlinebarber.Repository;

import com.rf.onlinebarber.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    public boolean existsByEmail(String email);
}
