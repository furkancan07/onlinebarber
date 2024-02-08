package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.AddShavingModelRequest;
import com.rf.onlinebarber.Dto.ShavingModelResponse;
import com.rf.onlinebarber.Dto.UpdateModelRequest;
import com.rf.onlinebarber.Entity.ShavingModel;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Service.ShavingModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/smodel")
@RequiredArgsConstructor
public class ShavingModelController {
    private final ShavingModelService shavingModelService;
    // model ekleme
    @PostMapping("/add/{id}")
    @PreAuthorize("#id==authentication.principal.id")
    public ResponseEntity<?> addShavingModel(@Valid @RequestBody AddShavingModelRequest request, @PathVariable Long id) {
        return shavingModelService.addShavingModel(request, id);
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
    @GetMapping("/list/{shopId}")
    public Page<ShavingModelResponse> getModelList(@PathVariable Long shopId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size){
        return shavingModelService.getModelList(shopId,page,size);
    }
    // bir tıraş bilgilerini görüntüleme
    @GetMapping("/{id}")
    public ShavingModelResponse getModel(@PathVariable Long id){
        return shavingModelService.getModel(id);
    }
}
