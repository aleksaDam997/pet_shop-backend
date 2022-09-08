
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
