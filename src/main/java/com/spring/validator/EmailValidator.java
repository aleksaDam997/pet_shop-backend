
package com.spring.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements Predicate<String>{

	@Override
	public boolean test(String t) {
		// TODO Auto-generated method stub
		return true;
	}

}
