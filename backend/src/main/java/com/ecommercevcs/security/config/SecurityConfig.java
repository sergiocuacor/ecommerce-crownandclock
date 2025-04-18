package com.ecommercevcs.security.config;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
	    //    .cors(cors -> cors.configurationSource(corsConfigurationSource())) CONFIGURACIÓN DEL CORS, TODOS LOS MÉTODOS DE ABAJO
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones
	        .authorizeHttpRequests(auth -> auth
	        		// Endpoints públicos para BrandController
	                .requestMatchers(HttpMethod.GET, "/brands/all").permitAll()
	                .requestMatchers(HttpMethod.GET, "/brands/{brandId}").permitAll()
	                .requestMatchers(HttpMethod.GET, "/brands/detailed/all").permitAll()
	                .requestMatchers(HttpMethod.GET, "/brands/detailed/{id}").permitAll()
	                .requestMatchers(HttpMethod.GET, "/brands/{brandName}/products").permitAll()
	                
	                // Endpoints públicos para ProductController
	                .requestMatchers(HttpMethod.GET, "/products").permitAll()
	                .requestMatchers(HttpMethod.GET, "/products/all").permitAll()
	                .requestMatchers(HttpMethod.GET, "/products/{id}").permitAll()
	                .requestMatchers(HttpMethod.GET, "/products/mask/{mask}").permitAll()
	                .requestMatchers(HttpMethod.GET, "/products/stock/{productId}").permitAll()
	                
	                // Endpoints públicos para ReviewController
	                .requestMatchers(HttpMethod.GET, "/reviews/product/{productId}").permitAll()
	                
	                // Endpoints que requieren rol ADMIN para BrandController
	                .requestMatchers(HttpMethod.POST, "/brands").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE, "/brands/{id}").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/brands/{id}").hasRole("ADMIN")
	                
	                // Endpoints que requieren rol ADMIN para ProductController
	                .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/products/{id}").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.POST, "/products/{brandId}").hasRole("ADMIN")
	                
	                // Endpoints que requieren ADMIN para OrderController
	                .requestMatchers(HttpMethod.GET, "/orders/all").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/orders/{id}").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE, "/orders/{id}").hasRole("ADMIN")
	                
	                // Endpoints que requieren autenticación (ADMIN o USER) para OrderController
	                .requestMatchers(HttpMethod.GET, "/orders/{id}").hasAnyRole("ADMIN", "USER")
	                .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("ADMIN", "USER")
	                .requestMatchers(HttpMethod.GET, "/orders/discounts/{userId}").hasAnyRole("ADMIN", "USER")
	                
	                // Endpoints que requieren autenticación (ADMIN o USER) para ReviewController
	                .requestMatchers(HttpMethod.POST, "/reviews/{userId}/{productId}").hasAnyRole("ADMIN", "USER")
	                .requestMatchers(HttpMethod.GET, "/reviews/user/{userId}").hasAnyRole("ADMIN", "USER")
	                .requestMatchers(HttpMethod.PUT, "/reviews/{reviewId}/user/{userId}").hasAnyRole("ADMIN", "USER")
	                .requestMatchers(HttpMethod.DELETE, "/reviews/{reviewId}/user/{userId}").hasAnyRole("ADMIN", "USER")
	                
	                // Por defecto, cualquier otra solicitud requiere autenticación
	                .anyRequest().authenticated()
	        )
	        .addFilter(new JWTAuthenticationFilter(authenticationManager)) // Agregar el filtro de autenticación JWT
	        .addFilter(new JWTValidationFilter(authenticationManager))
	        .build();
	    
	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowedOriginPatterns(Arrays.asList("*"));
//		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//		config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//		config.setAllowCredentials(true);
//		
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", config);
//		
//		return source;
//		
//	}
//	
//	@Bean
//	FilterRegistrationBean<CorsFilter> corsFilter(){
//		FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
//		
//		corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		
//		return corsBean;
//	}
//	
	


}
