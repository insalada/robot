package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not valid orientation")  
public class InvalidInputOrientationException extends InvalidInputException{
	
	private static final long serialVersionUID = 4743561757063217970L;

	public InvalidInputOrientationException() {
		super("Not a valid orientation provided (N, S, E, W). Eg: 1 2 N");
	}

	public InvalidInputOrientationException(String message) {
		super(message);
	}
}
