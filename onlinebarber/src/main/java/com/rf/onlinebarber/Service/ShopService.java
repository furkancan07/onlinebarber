package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Dto.OpenShopRequest;
import com.rf.onlinebarber.Dto.UpdateShopRequest;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Exception.ShopNotFoundException;
import com.rf.onlinebarber.Repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<?> openShop(OpenShopRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Shop shop=Shop.builder().name(request.getName()).email(request.getEmail()).
                image(request.getImage()).
                password(request.getPassword()).phoneNumber(request.getPhoneNumber()).
                build();
        shopRepository.save(shop);
        return ResponseEntity.ok("Mağaza Açıldı");
    }

    public ResponseEntity<?> deleteShop(Long id) {
        Shop shop=shopRepository.findById(id).orElseThrow(()->new ShopNotFoundException(id));
        shopRepository.delete(shop);
        return ResponseEntity.ok(id+" numaralı mağaza silindi");
    }
    public ResponseEntity<?> updateShop(Long id, UpdateShopRequest request) {
        Shop shop=shopRepository.findById(id).orElseThrow(()->new ShopNotFoundException(id));
        shop.setPassword(passwordEncoder.encode(request.getPassword()));
        shop.setName(request.getName());
        shop.setEmail(request.getEmail());
        shop.setImage(request.getImage());
        shop.setPhoneNumber(request.getPhoneNumber());
        shopRepository.save(shop);
        return ResponseEntity.ok("Mağaza Bilgileri Güncellendi");
    }
}
