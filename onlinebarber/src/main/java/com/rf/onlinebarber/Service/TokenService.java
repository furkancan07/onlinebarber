package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Entity.Token;
import com.rf.onlinebarber.Exception.TokenException;
import com.rf.onlinebarber.Repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    // create-- login
    public Token createToken(Shop shop){
        String val= UUID.randomUUID().toString();
        Token token=Token.builder().token(val).shop(shop).build();
        tokenRepository.save(token);
        return token;
    }
    // tokeni doğrulama
    public Shop verifyToken(String token){
        Token db=getToken(token);
        return db.getShop();
    }
    // çıkış
    public void logout(String token){
        Token db=getToken(token);
        tokenRepository.delete(db);
    }
    // tokeni al
    private Token getToken(String token){
        return tokenRepository.findById(token).orElseThrow(()->new TokenException());
    }
}
