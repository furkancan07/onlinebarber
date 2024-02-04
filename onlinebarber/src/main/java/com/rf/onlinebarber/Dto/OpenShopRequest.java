package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Validation.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OpenShopRequest {
    private String name;

    @UniqueEmail
    @Email
    private String email;
    @NotNull
    @Size(min = 8,max = 256)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$" ,message = "Lütfen en az bir büyük harf,bir küçük harf ve sayi kullanin")
    private String password;
    @Lob
    private String image;
    @NotNull
    private String phoneNumber;
}
