package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception base clase for any input error
 * @author insalada
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not valid input") 
public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = 1885157310823508461L;

	
	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(Throwable e) {
		super(e);
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
	
}
