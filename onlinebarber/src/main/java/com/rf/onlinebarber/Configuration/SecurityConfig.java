package com.rf.onlinebarber.Configuration;

import com.rf.onlinebarber.Component.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.RequestMatcherRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*herkese açık
 *
 *
 *
 * */
/*kapalı
 *http://localhost:8080/api/v1/shop/delete/{id}
 * http://localhost:8080/api/v1/shop/update/{id}
 *http://localhost:8080/api/v1/appointment/list/{id}
 *http://localhost:8080/api/v1/smodel/add/{id}
 * http://localhost:8080/api/v1/smodel/delete/{id}
 *http://localhost:8080/api/v1/smodel/update/{id}
 *
 */
@Configuration
@EnableWebSecurity
@RestController
public class SecurityConfig {
    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((a) ->a.requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/shop/delete/{id}")).authenticated()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/shop/update/{id}")).authenticated()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/appointment/list/{id}")).authenticated()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/smodel/add/{id}")).authenticated()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/smodel/delete/{id}")).authenticated()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/smodel/update/{id}")).authenticated()
                        .anyRequest().permitAll());

       // http.httpBasic(x -> x.authenticationEntryPoint(authEntryPoint));
        http.csrf(x -> x.disable());
        http.headers(x -> x.disable());
        http.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      //  http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

/*

 */
