package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not valid move") 
public class InvalidInputMoveException extends InvalidInputException {

	private static final long serialVersionUID = -5584673104812687604L;

	public InvalidInputMoveException() {
		super("Invalid move. (E.g: LMLMLMRMM)");
	}
	
	public InvalidInputMoveException(String message) {
		super(message);
	}
}
