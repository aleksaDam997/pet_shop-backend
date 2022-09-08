<<<<<<< HEAD
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
=======
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
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
