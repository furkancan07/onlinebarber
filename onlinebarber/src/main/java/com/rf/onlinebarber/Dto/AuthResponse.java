package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Entity.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private ShopResponse shop;
    private Token token;
}
