package com.ecommercevcs.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.security.config.TokenJWTConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String header = request.getHeader(TokenJWTConstants.HEADER_AUTHORIZATION);
        Map<String, Object> response = new HashMap<>();
        
        if (header == null || !header.startsWith(TokenJWTConstants.PREFIX_TOKEN)) {
            response.put("valid", false);
            response.put("message", "No token provided or invalid token format");
            return ResponseEntity.ok(response);
        }
       
        String token = header.replace(TokenJWTConstants.PREFIX_TOKEN, "");
        
        try {
            // Comprobamos la firma con la SecretKey, si ha expirado o ha sido manipulada tira excepción
            Claims claims = Jwts.parser()
                .verifyWith(TokenJWTConstants.SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
            
            // Si llegamos aquí, el token es válido
            response.put("valid", true);
            response.put("email", claims.getSubject());
           
            
            ZoneId localZone = ZoneId.systemDefault(); 
            Date expirationUtc = claims.getExpiration();
            ZonedDateTime expirationLocal = expirationUtc.toInstant().atZone(localZone);
            response.put("expiration", expirationLocal);
            
            return ResponseEntity.ok(response);
            
        } catch (ExpiredJwtException e) {
            
            response.put("valid", false);
            response.put("message", "Token has expired");
            return ResponseEntity.ok(response);
            
        } catch (JwtException e) {
            response.put("valid", false);
            response.put("message", "Invalid token: " + e.getMessage());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("valid", false);
            response.put("message", "Error validating token: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
	
}
