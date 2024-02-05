package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Entity.ShavingModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAppointmentRequest {
    @NotNull
    private LocalDateTime dateTime;
}
