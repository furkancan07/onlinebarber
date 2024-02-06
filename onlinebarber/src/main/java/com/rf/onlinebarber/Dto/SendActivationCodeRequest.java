package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Validation.CustomerUniqueEmail;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendActivationCodeRequest {
    @CustomerUniqueEmail
    @Email
    private String email;
}
