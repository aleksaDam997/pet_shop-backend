<<<<<<< HEAD
package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup findByGroupName(String groupName);
}
=======
package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup findByGroupName(String groupName);
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
