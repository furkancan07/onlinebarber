package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Configuration.BeanConfig;
import com.rf.onlinebarber.Dto.DtoConverter;
import com.rf.onlinebarber.Dto.OpenShopRequest;
import com.rf.onlinebarber.Dto.ShopResponse;
import com.rf.onlinebarber.Dto.UpdateShopRequest;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Exception.AuthorizationException;
import com.rf.onlinebarber.Exception.ShopNotFoundException;
import com.rf.onlinebarber.Repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;
    private final DtoConverter converter;
    private final BeanConfig config;

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
        Shop control=config.getAuthentication();
        Shop shop=shopRepository.findById(id).orElseThrow(()->new ShopNotFoundException(id));
        if(!config.isAuthenticate(control,shop)) throw new AuthorizationException();
        shopRepository.delete(shop);
        return ResponseEntity.ok(id+" numaralı mağaza silindi");
    }
    public ResponseEntity<?> updateShop(Long id, UpdateShopRequest request) {
        Shop control=config.getAuthentication();
        Shop shop=shopRepository.findById(id).orElseThrow(()->new ShopNotFoundException(id));
        if(config.isAuthenticate(control,shop)) throw new AuthorizationException();
        shop.setPassword(passwordEncoder.encode(request.getPassword()));
        shop.setName(request.getName());
        shop.setEmail(request.getEmail());
        shop.setImage(request.getImage());
        shop.setPhoneNumber(request.getPhoneNumber());
        shopRepository.save(shop);
        return ResponseEntity.ok("Mağaza Bilgileri Güncellendi");
    }
    public Page<ShopResponse> getShopList(int page, int size) {
        Page<Shop> shopList=shopRepository.findAll(PageRequest.of(page,size));
        return shopList.map(converter::shopConverter);
    }

    public Shop findById(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(()->new ShopNotFoundException());
    }
}
