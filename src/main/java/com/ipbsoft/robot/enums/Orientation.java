package com.ipbsoft.robot.enums;

import java.util.Arrays;

import com.ipbsoft.robot.exceptions.InvalidInputException;
import com.ipbsoft.robot.exceptions.InvalidInputOrientationException;
import com.ipbsoft.robot.implementations.CircularList;

/**
 * A class with static methods to provide Orientation implementation.
 * <p>
 * <code>N</code> stands for <i>NORTH</i>
 * <code>E</code> stands for <i>EAST</i>
 * <code>S</code> stands for <i>SOUTH</i>
 * <code>W</code> stands for <i>WEST</i>
 * @author insalada
 *
 */
public enum Orientation { 
	/** North */
	N,
	/** East */
	E,
	/** South */
	S,
	/** West */
	W;
	
	private static CircularList<Orientation> orientations =
			new CircularList<>(Arrays.asList(Orientation.values()));
	
	/**
	 * Returns an Orientation object based on the given string
	 * @param an orientation in string format
	 * @return Orientation found
	 * @throws InvalidInputOrientationException if not found
	 */
	public static Orientation lookup(String str) throws InvalidInputOrientationException {
		try {
			return Orientation.valueOf(str);
		} catch(IllegalArgumentException e) {
			throw new InvalidInputOrientationException();
		}
	}
	
	/**
	 * Parses the provided input in string format into a valid Orientation object.
	 * @param input
	 * @return Orientation
	 * @throws InvalidInputException
	 */
	public static Orientation parseOrientation(String input) throws InvalidInputException {
		if(input == null || input.length() < 5 || input.split(" ").length < 2)
			throw new InvalidInputOrientationException();
		
		return Orientation.lookup(input.split(" ")[2]);
	}
	
	/**
	 * Returns the Robot orientation when turning left
	 * based on current orientation.
	 * @param orientation
	 * @return Orientation
	 */
	public static Orientation turnLeft(Orientation orientation) {
		return orientations.previous(orientation);
	}
	
	/**
	 * Returns the Robot orientation when turning right
	 * based on current orientation.
	 * @param orientation
	 * @return Orientation
	 */
	public static Orientation turnRight(Orientation orientation) {
		return orientations.next(orientation);
	}
 }