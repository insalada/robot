package com.ipbsoft.robot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception happens when there is no robots available to play with at the plateau
 * 
 * @author insalada
 *
 */

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Robot deployed")
public class NoRobotsDeployedException extends NoResourceAvailableException {

	private static final long serialVersionUID = -6633269928270690532L;

	public NoRobotsDeployedException() {
		super("No robots deployed in the plateau. Have you added any?");
	}
}
