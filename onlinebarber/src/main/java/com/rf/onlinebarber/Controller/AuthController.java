package com.rf.onlinebarber.Controller;

import com.rf.onlinebarber.Dto.AuthRequest;
import com.rf.onlinebarber.Dto.AuthResponse;
import com.rf.onlinebarber.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    // Shoplogin
    @PostMapping("/login")

    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest){
        AuthResponse authResponse=authService.login(authRequest);
        ResponseCookie cookie=ResponseCookie.from("login-token",authResponse.getToken().getToken()).path("/").httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(authResponse);
    }
    // Shoplogout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(name = "login-token",required = false) String cookie){
        authService.logout(cookie);
        ResponseCookie cookie1=ResponseCookie.from("login-token","").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie1.toString()).body("Çıkış Yapıldı");
    }

}
