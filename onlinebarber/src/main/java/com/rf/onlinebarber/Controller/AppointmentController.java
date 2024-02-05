package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.CreateAppointmentRequest;
import com.rf.onlinebarber.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    // randevu oluştur customerId,modelId
    @PostMapping("/create/{customerId}/{modelId}")
    public ResponseEntity<?> createAppointment(@PathVariable Long customerId, @PathVariable Long modelId,@Valid @RequestBody CreateAppointmentRequest request){
        return appointmentService.createAppointment(customerId,modelId,request);
    }
    // randevu iptal etme
    // mağaza tarafından gelen randevuları görme

}
