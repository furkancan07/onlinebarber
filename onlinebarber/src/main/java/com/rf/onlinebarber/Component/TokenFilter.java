package com.rf.onlinebarber.Component;

import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String token=getToken(request);

    if (token != null) {
        Shop shop = tokenService.verifyToken(token);
        if (shop != null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(shop, null, shop.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

}
      filterChain.doFilter(request,response);
    }
    private String getToken(HttpServletRequest request){
String prefix=request.getHeader("Authorization");
        var cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cook : cookies){
                if(cook.getName().equals("login-token") && (cook.getValue()!=null || !cook.getValue().isEmpty())){
                    return cook.getValue();
                }
            }
        }
        return prefix;
    }

}
