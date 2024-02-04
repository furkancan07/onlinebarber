package com.rf.onlinebarber.Dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShopRequest {
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 8,max = 256)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$" ,message = "Lütfen en az bir büyük harf,bir küçük harf ve sayi kullanin")
    private String password;
    private String image;
    @NotNull
    private String phoneNumber;
}
