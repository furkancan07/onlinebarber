package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Dto.AuthRequest;
import com.rf.onlinebarber.Dto.AuthResponse;
import com.rf.onlinebarber.Dto.DtoConverter;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Entity.Token;
import com.rf.onlinebarber.Exception.AuthException;
import com.rf.onlinebarber.Exception.ShopNotFoundException;
import com.rf.onlinebarber.Repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ShopRepository shopRepository;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final DtoConverter converter;
    public AuthResponse login(AuthRequest authRequest) {
        Shop shop=shopRepository.findByEmail(authRequest.getEmail()).orElseThrow(()->new ShopNotFoundException());
        if(!encoder.matches(authRequest.getPassword(),shop.getPassword())) throw new AuthException();
       Token token= tokenService.createToken(shop);

       shopRepository.save(shop);
        AuthResponse authResponse=AuthResponse.builder().token(token).shop(converter.shopConverter(shop)).build();
        return authResponse;
    }

    public void logout(String cookie) {
        tokenService.logout(cookie);
    }
}
