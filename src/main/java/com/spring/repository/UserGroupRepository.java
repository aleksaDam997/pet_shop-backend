package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup findByGroupName(String groupName);
}
