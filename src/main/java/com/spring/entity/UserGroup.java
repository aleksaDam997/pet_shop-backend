<<<<<<< HEAD
package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userGroupId;
	private String  groupName;
	private String description;
}
=======
package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userGroupId;
	private String  groupName;
	private String description;
}
>>>>>>> 1b1b9033059a8b7e6d9387fdb058205b0911bfe6
