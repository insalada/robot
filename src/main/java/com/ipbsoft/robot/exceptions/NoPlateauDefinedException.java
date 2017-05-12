package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Plateau not defined") 
public class NoPlateauDefinedException extends NoResourceAvailableException {

	private static final long serialVersionUID = 1731941676670246479L;
	
	public NoPlateauDefinedException() {
		super("Plateau bounds not defined yet");
	}

}
