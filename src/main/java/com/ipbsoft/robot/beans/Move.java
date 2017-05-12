package com.ipbsoft.robot.beans;

import com.ipbsoft.robot.enums.Orientation;
import com.ipbsoft.robot.exceptions.InvalidInputMoveException;

/**
 * A Move scenario happens with a specific Robot in the Plateau
 * and a set of instructions.
 * 
 * @author insalada
 *
 */
public class Move {
	private String instructions;
	private Plateau plateau;
	private Robot robot;
	public static char INSTRUCTION_MOVE = 'M', 
			INSTRUCTION_TURN_LEFT = 'L', 
			INSTRUCTION_TURN_RIGHT = 'R';
	
	public Move(Plateau plateau, Robot robot, String instructions) {
		this.plateau = plateau;
		this.robot = robot;
		this.instructions = instructions;
	}

	/**
	 * Performs all the instructions sequentially by the given input.
	 */
	public synchronized void processMoves() {
		for(char move : instructions.toCharArray()) {
			moveRobot(move);
		}
	}
	
	/**
	 * Perform the move based on the input char
	 * <p>
	 * Options are: <code>L</code>(Left), <code>R</code>(Right), <code>M</code>(Move)
	 * @param move
	 */
	private void moveRobot(char move) {
		if(move == INSTRUCTION_MOVE)
			advanceRobot();
		else
			robot.rotate(move);
	}
	
	/**
	 * Advances the robot one cell towards its orientation<br>
	 * If the new position is out of the plateau boundaries, it will stop right there
	 */
	private void advanceRobot() {
		Coordinate origin = robot.getPosition();
		if(origin != null) {
			Coordinate destination = null;
			if (robot.getOrientation() == Orientation.N)
				destination = origin.getPositionUp();
			else if (robot.getOrientation() == Orientation.S)
				destination = origin.getPositionDown();
			else if (robot.getOrientation() == Orientation.W)
				destination = origin.getPositionLeft();
			else if (robot.getOrientation() == Orientation.E)
				destination = origin.getPositionRight();

			if (plateau.isWithinBounds(destination))
				robot.setPosition(destination);
		}
	}
	
	/**
	 * Check whether the given input is correct. 
	 * <p>
	 * (Only <code>L</code>, <code>R</code> and <code>M</code> accepted)
	 * 
	 * @param input
	 * @return true if correct, false otherwise
	 * @throws InvalidInputMoveException
	 */
	public static void validate(String input) throws InvalidInputMoveException {
		if(input == null || input.isEmpty())
			throw new InvalidInputMoveException();
		
		for(char move : input.toCharArray()) {
			if(move != 'L' && move != 'R' && move != 'M')
				throw new InvalidInputMoveException();
		}
	}
	
	/**
	 * Returns the current robot position and orientation with the proper output format
	 * @return String with the output
	 */
	public String output() {
		return new StringBuffer()
				.append(robot.getPosition().getX())
				.append(Coordinate.SEPARATOR)
				.append(robot.getPosition().getY())
				.append(Coordinate.SEPARATOR)
				.append(robot.getOrientation())
				.toString();
	}

	@Override
	public String toString() {
		return "Move [moves=" + instructions + ", plateau=" + plateau + ", robot=" + robot + "]";
	}
}
