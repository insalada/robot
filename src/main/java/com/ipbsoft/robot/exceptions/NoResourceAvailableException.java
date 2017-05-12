package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception base class for any not source exception kind of
 * @author insalada
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource not available") 
public class NoResourceAvailableException extends Exception {
	
	private static final long serialVersionUID = -4350365857267583908L;

	public NoResourceAvailableException() {
		super();
	}
	
	public NoResourceAvailableException(Throwable e) {
		super(e);
	}
	
	public NoResourceAvailableException(String message) {
		super(message);
	}
}
