package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.OrderStatus;
import com.spring.entity.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
	
	@Query("SELECT uo FROM UserOrder uo WHERE uo.status = ?1")
	List<UserOrder> getOrdersByStatus(OrderStatus orderStatus);
	
	@Query("SELECT uo FROM UserOrder uo JOIN uo.cart c JOIN c.user u WHERE u.userId = ?1")
	List<UserOrder> getOrdersByUserId(Long userId);

}
