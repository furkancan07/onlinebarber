package com.rf.onlinebarber.Configuration;

import com.rf.onlinebarber.Entity.Shop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Shop getAuthentication(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Shop shop=(Shop) authentication.getPrincipal();
        return shop;
    }
    public boolean isAuthenticate(Shop control,Shop request){
        return control.getId()== request.getId();
    }

}
