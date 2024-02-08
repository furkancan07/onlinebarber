package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Exception.ShopNotFoundException;
import com.rf.onlinebarber.Repository.ShopRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    private final ShopRepository shopRepository;

    public AppUserDetailService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return shopRepository.findByEmail(email).orElseThrow(()->new ShopNotFoundException());
    }
}
