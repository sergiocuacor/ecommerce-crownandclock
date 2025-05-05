package com.ecommercevcs.security.config.filters;

import java.io.IOException;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommercevcs.entities.UserEntity;
import static com.ecommercevcs.security.config.TokenJWTConstants.*;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private AuthenticationManager authenticationManager;
	

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserEntity user = null;
		String email = null; // SI NO FUNCIONA CON USERNAME PROBAR EMAIL
		String password = null;

		try {
			user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
			email = user.getEmail();
			password = user.getPassword();
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);

		return this.authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		String email = user.getUsername(); //ES EL EMAIL, NO EL USERNAME!!!!!
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("authorities", roles); 
		
		String token = Jwts.builder() //PODRIAMOS GENERAR EL TOKEN SOLO CON LAS CLAIMS .claims(claims) expiration, subject, date...etc
				.subject(email)
				.claims(claims)
				.expiration(new Date(System.currentTimeMillis() + 3600000)) //ESTO INDICA QUE EXPIRA EN UNA HORA (3600000)
				.issuedAt(new Date()) //FECHA AHORA
				.signWith(SECRET_KEY)
				.compact();
		
		response.addHeader(HEADER_AUTHORIZATION,PREFIX_TOKEN + token);
		
		Map<String, String> body = new HashMap<String, String>();
		
		body.put("token", token);
		body.put("email", email);
		body.put("message", String.format("Hola  " + email + " has iniciado sesión con éxito"));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType(CONTENT_TYPE);
		response.setStatus(200);
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, String> body = new HashMap<String, String>();
		body.put("message", "error en la autenticación");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType(CONTENT_TYPE);
	}

	
	

}
