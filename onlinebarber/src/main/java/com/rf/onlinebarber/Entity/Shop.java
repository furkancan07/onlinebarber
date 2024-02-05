package com.rf.onlinebarber.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rf.onlinebarber.Validation.UniqueEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Lob
    private String image;
    private String phoneNumber;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ShavingModel> shavingModels;

}
