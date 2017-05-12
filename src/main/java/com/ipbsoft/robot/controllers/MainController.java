package com.ipbsoft.robot.controllers;

import com.ipbsoft.robot.beans.Coordinate;
import com.ipbsoft.robot.beans.Move;
import com.ipbsoft.robot.beans.Plateau;
import com.ipbsoft.robot.beans.Robot;
import com.ipbsoft.robot.enums.Orientation;
import com.ipbsoft.robot.exceptions.InvalidInputException;
import com.ipbsoft.robot.exceptions.NoPlateauDefinedException;
import com.ipbsoft.robot.exceptions.NoResourceAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * A unique controller for all the requests. Services are described below:<br>
 * 
 * <b>url, description</b><br>
 * <code>/start</code>: Set a new plateau boundaries<br>
 * <code>/deploy</code>: Deploys a robot<br>
 * <code>/move</code>: Makes a move<br>
 * @author insalada
 *
 */
@RestController
public class MainController {
	private Plateau plateau;

	@RequestMapping(value="/start", method=RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public String start(@RequestBody String input) throws InvalidInputException {
		plateau = new Plateau(Coordinate.parseCoordinates(input));
		return plateau.toString();
	}
	
	@RequestMapping(value="/deploy", method=RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public String addRobot(@RequestBody String input)
			throws InvalidInputException, NoPlateauDefinedException {
		if(plateau == null)
			throw new NoPlateauDefinedException();
		
		Coordinate robotPosition = Coordinate.parseCoordinates(input);
		Robot robot = new Robot(Orientation.parseOrientation(input));
		plateau.validate(robotPosition);
		plateau.addRobot(robot, robotPosition);
		return robot.toString();
	}
	
	@RequestMapping(value="/move", method=RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String move(@RequestBody String input) 
			throws InvalidInputException, NoResourceAvailableException {
		if(plateau == null)
			throw new NoPlateauDefinedException();
		
		Move.validate(input);
		Move move = new Move(plateau, plateau.pullRobot(), input);
		move.processMoves();
		return move.output();
	}
	
	/**
	 * An spring exception handlers for all kind of InvalidInputExceptions
	 * 
	 * @param InvalidInputException
	 * @return a body in string format with the error message
	 */
	@ExceptionHandler(InvalidInputException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleInvalidInput(InvalidInputException e)   {
		//LOGGER.error("Input not valid: " + e.getMessage());
		return e.getMessage();
	}
	
	/**
	 * An spring exception handler for all kind of NoResourceAvailableException
	 * 
	 * @param NoResourceAvailableException
	 * @return a body in string format with the error message
	 */
	@ExceptionHandler(NoResourceAvailableException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleNoResourceAvailable(NoResourceAvailableException e)   {
		return e.getMessage();
	}	
}
