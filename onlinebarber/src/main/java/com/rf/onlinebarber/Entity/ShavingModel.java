package com.rf.onlinebarber.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShavingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String image;
    private int price;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "shopId")
    private Shop shop;
}
