package com.rf.onlinebarber.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rf.onlinebarber.Validation.UniqueEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop implements UserDetails {
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
   @OneToMany(mappedBy = "shop",cascade = CascadeType.REMOVE)
   @JsonIgnore
   private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
