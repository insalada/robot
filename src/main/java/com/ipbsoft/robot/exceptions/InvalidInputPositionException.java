package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not valid position") 
public class InvalidInputPositionException extends InvalidInputException {
	
	private static final long serialVersionUID = -7233538249562534477L;

	public InvalidInputPositionException() {
		super("Invalid input for Robot location. (E.g: 1 2 N)");
	}
	
	public InvalidInputPositionException(String message) {
		super(message);
	}
}
