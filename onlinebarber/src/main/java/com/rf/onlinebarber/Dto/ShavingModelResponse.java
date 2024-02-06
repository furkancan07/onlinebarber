package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShavingModelResponse {
    private Long id;
    private String name;

    private String image;
    private int price;

    private ShopResponse shop;
}
