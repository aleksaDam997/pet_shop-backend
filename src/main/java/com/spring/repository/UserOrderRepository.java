package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

}
