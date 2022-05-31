package com.spring.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.UrlPathHelper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {


		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authenticationToken);
	}
	
	
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		UrlPathHelper urlPathHelper = new UrlPathHelper();

	    logger.debug("failed authentication while attempting to access "
	            + urlPathHelper.getPathWithinApplication((HttpServletRequest) request));

	    //Add more descriptive message
	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
	            "Authentication Failed");
		
//		Map<String, String> tokens = new HashMap<>();
//		tokens.put("status", "login");
//		tokens.put("message", "Prijava nije spješna, pokušajte ponovo..");
//		
//		response.setContentType("application/json");
//		
//		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		User user = (User) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		
		String accessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 15 * 1000 * 60))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		
//		.withClaim("id", request.getRemoteAddr())

		
		String refreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 45 * 1000 * 60))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		
		Map<String, String> tokens = new HashMap<>();
		tokens.put("status", "ok");
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);
		
		response.setContentType("application/json");
		
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
}

