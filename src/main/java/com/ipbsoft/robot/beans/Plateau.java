package com.ipbsoft.robot.beans;

import java.util.LinkedList;
import java.util.Queue;

import com.ipbsoft.robot.exceptions.InvalidInputPositionException;
import com.ipbsoft.robot.exceptions.NoRobotsDeployedException;

/**
 * Represents the Plateau where the robots will be deployed.
 * <p>
 * The plateau boundaries are setted by its constructor when instantiated.<br>
 * Provides the methods for handling the queue of robots deployed inside.<br>
 * Provides methods to valide whether the plateau boundaries are correct and the robots are well placed all around the map
 * 
 * @author insalada
 *
 */
public class Plateau {
	private Coordinate bounds;
	private Queue<Robot> robots;

	/**
	 * The unique constructor creates a plateau terrain setting the given position as the plateau bounds<br>
	 * starting from coordinate (0,0)
	 * <p>
	 * <i>E.g: if given bounds are (5,5), it will create a Plateu with size (6,6)</i><br>
	 * @param bounds
	 */
	public Plateau(Coordinate bounds) {
		this.bounds = bounds;
		robots = new LinkedList<>();
	}

	/**
	 * Adds a robot into the plateau queue of robots.<b>
	 * Sets the position of the robot in the plateau
	 * @param robot
	 * @param robotPosition
	 */
	public synchronized void addRobot(Robot robot, Coordinate robotPosition) {
		robot.setPosition(robotPosition);
		robots.add(robot);
	}
	
	/**
	 * Returns and removes the first Robot from the Plateau queue<br>
	 * <i>the <b>head</b> of the queue</i>
	 * @return Robot or null if empty
	 * @throws NoRobotsDeployedException
	 */
	public synchronized Robot pullRobot() throws NoRobotsDeployedException {
		if(robots == null || robots.isEmpty())
			throw new NoRobotsDeployedException();
		
		return robots.poll();
	}

	/**
	 * Check whether the Robot position is within the plateau boundaries
	 * 
	 * @param robotPosition
	 * @return true if it is inside the boundaries, false otherwise.
	 */
	public boolean isWithinBounds(Coordinate robotPosition) {
		if (robotPosition.getX() >= 0 && robotPosition.getY() >= 0
				&& robotPosition.getX() <= bounds.getX()
				&& robotPosition.getY() <= bounds.getY())
			return true;

		return false;
	}

	/**
	 * Checks whether Robot position is correct.
	 *
	 * @param robotPosition
	 * @return true if valid position, false otherwise
	 * @throws InvalidInputPositionException
	 */
	public void validate(Coordinate robotPosition) throws InvalidInputPositionException {
		if (robotPosition == null)
			throw new InvalidInputPositionException("Position must not be null");

		if (!isWithinBounds(robotPosition))
			throw new InvalidInputPositionException(
					"Invalid position. Pleateau boundaries are " + bounds);
	}
	
	@Override
	public String toString() {
		return "Plateau bounds are " + bounds;
	}
}