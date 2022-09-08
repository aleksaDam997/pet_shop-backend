<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
