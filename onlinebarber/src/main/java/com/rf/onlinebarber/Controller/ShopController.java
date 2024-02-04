package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.OpenShopRequest;
import com.rf.onlinebarber.Dto.UpdateShopRequest;
import com.rf.onlinebarber.Service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    // mağaza açma
    @PostMapping("/open")
    public ResponseEntity<?> openShop(@Valid @RequestBody OpenShopRequest request){
        return shopService.openShop(request);
    }
    // mağaza bilgilerini güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShop(@PathVariable Long id, @Valid @RequestBody UpdateShopRequest request){
        return shopService.updateShop(id,request);
    }
    // mağaza silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Long id){
        return shopService.deleteShop(id);
    }
    // tüm mağazalari listele


}
