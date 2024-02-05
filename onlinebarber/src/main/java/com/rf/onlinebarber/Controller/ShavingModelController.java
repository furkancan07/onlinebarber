package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.AddShavingModelRequest;
import com.rf.onlinebarber.Dto.UpdateModelRequest;
import com.rf.onlinebarber.Service.ShavingModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/smodel")
@RequiredArgsConstructor
public class ShavingModelController {
    private final ShavingModelService shavingModelService;
    // model ekleme
    @PostMapping("/add/{shopId}")
    public ResponseEntity<?> addShavingModel(@Valid @RequestBody AddShavingModelRequest request, @PathVariable Long shopId) {
        return shavingModelService.addShavingModel(request, shopId);
    }
    // model silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable Long id){
        return shavingModelService.deleteModel(id);
    }
    // model güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateModel(@PathVariable Long id, UpdateModelRequest request){
        return shavingModelService.updateModel(request,id);
    }
    // bir mağazaya ait saç modellerini listele
}
