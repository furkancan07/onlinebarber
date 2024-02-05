package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Dto.AddShavingModelRequest;
import com.rf.onlinebarber.Dto.UpdateModelRequest;
import com.rf.onlinebarber.Entity.ShavingModel;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Exception.ModelNotFoundException;
import com.rf.onlinebarber.Exception.ShopNotFoundException;
import com.rf.onlinebarber.Repository.ShavingModelRepository;
import com.rf.onlinebarber.Repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShavingModelService {

    private final ShavingModelRepository shavingModelRepository;
    private final ShopRepository shopRepository;

    public ResponseEntity<?> addShavingModel(AddShavingModelRequest request, Long shopId) {
        Shop shop=shopRepository.findById(shopId).orElseThrow(()->new ShopNotFoundException(shopId));
        ShavingModel shavingModel=ShavingModel.builder().shop(shop).
                name(request.getName()).price(request.getPrice()).image(request.getImage()).
                build();
        shavingModelRepository.save(shavingModel);
        return ResponseEntity.ok("Model kaydedildi");
    }

    public ResponseEntity<?> deleteModel(Long id) {
        ShavingModel shavingModel=shavingModelRepository.findById(id).orElseThrow(()->new ModelNotFoundException(id));
        shavingModelRepository.delete(shavingModel);
        return ResponseEntity.ok(id+" numaralı saç modeli silindi");
    }

    public ResponseEntity<?> updateModel(UpdateModelRequest request, Long id) {
        ShavingModel shavingModel=shavingModelRepository.findById(id).orElseThrow(()->new ModelNotFoundException(id));
        shavingModel.setName(request.getName());
        shavingModel.setImage(request.getImage());
        shavingModel.setPrice(request.getPrice());
        shavingModelRepository.save(shavingModel);
        return ResponseEntity.ok(id+" numaralı sakal modeli güncellendi");
    }

    public ShavingModel findById(Long id){
        return shavingModelRepository.findById(id).orElseThrow(()->new ModelNotFoundException(id));
    }
    public List<ShavingModel> shavingModelList(Long shopId){
        List<ShavingModel> list=new ArrayList<>();
        for(ShavingModel model : shavingModelRepository.findAll()){
            if(model.getShop().getId()==shopId){
                list.add(model);
            }
        }
        return list;
    }
}
