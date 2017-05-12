package com.ipbsoft.robot.beans;

import java.util.UUID;

import com.ipbsoft.robot.enums.Orientation;

public class Robot {
	
	private String id;
	private Coordinate position;
	private Orientation orientation;
		
	public Robot(Orientation orientation) {
		this.id = UUID.randomUUID().toString();
		this.orientation = orientation;
	}
	
	public String getId() {
		return id;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate coordinate) {
		this.position = coordinate;
	}
	
	/**
	 * Rotates the Robot based on the given direction<br>
	 * (<code>L</code> rotates left, <code>R</code> rotates right)
	 * @param direction
	 */
	public void rotate(char direction) {
		if(direction == Move.INSTRUCTION_TURN_LEFT)
			orientation = Orientation.turnLeft(orientation);
		else if(direction == Move.INSTRUCTION_TURN_RIGHT)
			orientation = Orientation.turnRight(orientation);
	}
	
	/**
	 * Rotates robot to the left
	 */
	public void rotateLeft() {
		orientation = Orientation.turnLeft(orientation);
	}
	
	/**
	 * Rotates robot to the right
	 */
	public void rotateRight() {
		orientation = Orientation.turnRight(orientation);
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
				.append("Robot at ")
				.append(position.getX())
				.append(Coordinate.SEPARATOR)
				.append(position.getY())
				.append(Coordinate.SEPARATOR)
				.append(orientation)
				.toString();
	}
	
	/**
	 * Bases Robot equality just on the id
	 * @param o
	 * @return true if same robot, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Robot))
			return false;
		
		return id == ((Robot) o).getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		return result;
	}
}
