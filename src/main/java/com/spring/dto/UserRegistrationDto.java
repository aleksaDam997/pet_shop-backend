
package com.spring.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegistrationDto {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String contact;
	private String address;
	private String role;
}
