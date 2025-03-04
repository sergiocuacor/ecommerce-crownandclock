package com.ecommercevcs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommercevcs.security.config.filters.JWTAuthenticationFilter;
import com.ecommercevcs.security.config.filters.JWTValidationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
	    return http
	        .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si usas JWT
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	            	"/**",
	                "/users", 
	                "/swagger-ui/**", 
	                "/v3/api-docs/**",   // Documentación OpenAPI
	                "/swagger-resources/**", 
	                "/webjars/**"         // Recursos estáticos de Swagger
	            ).permitAll() // Endpoints públicos
	            //.requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
//	            .requestMatchers(HttpMethod.POST, "/brand").hasRole("ADMIN") SOLO PUEDE CREAR BRANDS ADMIN
	           // .requestMatchers(HttpMethod.POST, "/brand/product/{id}").hasRole("ADMIN") NO VALE
	           // .requestMatchers(HttpMethod.PUT, "/brand").hasRole("ADMIN") 
	         // .requestMatchers(HttpMethod.PUT, "/product").hasRole("ADMIN") 
	          //  .requestMatchers(HttpMethod.PUT, "/product").hasRole("ADMIN")
	          //  .requestMatchers(HttpMethod.DELETE, "/product/{id}").hasRole("ADMIN")
	        //  .requestMatchers(HttpMethod.DELETE, "/brand/{id}").hasRole("ADMIN")
	            .anyRequest().authenticated() // Todo lo demás requiere autenticación
	        )
	        .addFilter(new JWTAuthenticationFilter(authenticationManager)) // Agregar el filtro de autenticación JWT
	        .addFilter(new JWTValidationFilter(authenticationManager))
	        .build();
	    
	}
	


}
