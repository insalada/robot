package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not valid coordinates") 
public class InvalidInputCoordinatesException extends InvalidInputException {
	
	private static final long serialVersionUID = -1637635518526297400L;

	public InvalidInputCoordinatesException() {
		super("Invalid coordinates (E.g: 1 2)");
	}
	
	public InvalidInputCoordinatesException(String message) {
		super(message);
	}
}
