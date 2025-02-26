package com.ecommercevcs.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf
//                .disable()
//            )
//            .headers(headers -> headers
//                .frameOptions(frameOptions -> frameOptions.disable())
//            )
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/h2-console/**").permitAll()
//                .anyRequest().permitAll()
//            );
//
//        return http.build();
//    }

	@Bean 
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	   
	   
	   return http.authorizeHttpRequests((auth) -> 
	   auth.requestMatchers("/users").permitAll()
	   .anyRequest().authenticated())
	   .csrf(config -> config.disable())
	   .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	   .build();
	   
   }
}
