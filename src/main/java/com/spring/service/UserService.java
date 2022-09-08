<<<<<<< HEAD
package com.spring.service;

import java.util.List;

import com.spring.dto.UserRegistrationDto;
import com.spring.entity.User;
import com.spring.entity.UserGroup;

public interface UserService {
	
	String register(UserRegistrationDto urd);
	UserGroup SaveUserGroup(UserGroup userGroup);
	void addUserGroupToUser(String username, String groupName);
	User getUser(String username);
	List<User> getUsers();
	String confirmToken(String token);
	User getUserByUsername(String username);

}
=======
package com.spring.service;

import java.util.List;

import com.spring.dto.UserRegistrationDto;
import com.spring.entity.User;
import com.spring.entity.UserGroup;

public interface UserService {
	
	String register(UserRegistrationDto urd);
	UserGroup SaveUserGroup(UserGroup userGroup);
	void addUserGroupToUser(String username, String groupName);
	User getUser(String username);
	List<User> getUsers();
	String confirmToken(String token);
	User getUserByUsername(String username);

}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
