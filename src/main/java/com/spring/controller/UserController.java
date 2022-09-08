package com.spring.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.dto.UserRegistrationDto;
import com.spring.entity.User;
import com.spring.entity.UserGroup;
import com.spring.service.UserService;

import ch.qos.logback.classic.Logger;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private final UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(this.userService.getUsers());
	}
	
	@GetMapping("/users/get")
	public ResponseEntity<User> getUser(@RequestBody GetUserByUsernameForm username) {
		
		return ResponseEntity.ok().body(this.userService.getUser(username.getUsername()));
	}
	
	@PostMapping("/user/registration")
	public ResponseEntity<String> register(@RequestBody UserRegistrationDto urd) {
		URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		return ResponseEntity.created(uri).body(this.userService.register(urd));
	}
	
	@PostMapping("/user/save/group")
	public ResponseEntity<UserGroup> saveuser(@RequestBody UserGroup userGroup) {
		URI uri = java.net.URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/group/save").toUriString());
		return ResponseEntity.created(uri).body(this.userService.SaveUserGroup(userGroup));
	}
	
	@GetMapping("/user/registration/confirm")
	public String confirm(@RequestParam("token") String token) {
		return this.userService.confirmToken(token);
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				
				String username = decodedJWT.getSubject();
				User user = this.userService.getUserByUsername(username);

				String accessToken = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 4 * 1000 * 60))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(UserGroup::getGroupName).collect(Collectors.toList()))
						.sign(algorithm);
				
				String refreshTokenToResponse = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 45 * 1000 * 60))
						.withIssuer(request.getRequestURL().toString())
						.sign(algorithm);
				
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", refreshTokenToResponse);
				
				response.setContentType("application/json");
				
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				
			}catch (Exception e) {
//				Logger.error("Error logging in {}", e.getMessage());
				response.setHeader("error", e.getMessage());
				response.setStatus(403);
//				response.sendError(FORBIDDEN.value()); FORBIDDEN.value() = 403
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage() + " SARMICA");
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), error);

			}
		}else {
			throw new RuntimeException("Refresh token is missing..");
		}
	}
	
	@PostMapping("/user/add/privileges")
	public ResponseEntity<?> addUserGroupToUSer(@RequestBody UserGroupToUserForm form) {
		this.userService.addUserGroupToUser(form.getUsername(), form.getGroupName());
		return ResponseEntity.ok().build();
	}
	
	@Data
	class UserGroupToUserForm{
		private String username;
		private String groupName;
	}
	
	@Data
	class GetUserByUsernameForm {
		private String username;
	}
}
