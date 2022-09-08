<<<<<<< HEAD
package com.spring.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.spring.entity.User;
import com.spring.entity.UserOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	
	private Long cartId;
	
    private Timestamp createdAt;
    
    private User user;

    private UserOrder userOrder;
    
	private Set<PetDto> pets = new HashSet<>();
	
}
=======
package com.spring.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.spring.entity.User;
import com.spring.entity.UserOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	
	private Long cartId;
	
    private Timestamp createdAt;
    
    private User user;

    private UserOrder userOrder;
    
	private Set<PetDto> pets = new HashSet<>();
	
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
