package com.ipbsoft.robot.beans;

import com.ipbsoft.robot.exceptions.InvalidInputCoordinatesException;
import com.ipbsoft.robot.exceptions.InvalidInputException;

/**
 * Represents an (x, y) position coordinates
 * @author insalada
 *
 */
public class Coordinate {
	private int x;
	private int y;
	public static String SEPARATOR = " ";
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns the position up from the given position
	 * @return coordinate
	 */
	public Coordinate getPositionUp() {
		return new Coordinate(this.x, this.y + 1);
	}
	
	/**
	 * Returns the position down from the given position
	 * @return coordinate
	 */
	public Coordinate getPositionDown() {
		return new Coordinate(this.x, this.y - 1);
	}
	
	/**
	 * Returns the position left from the given position
	 * @return coordinate
	 */
	public Coordinate getPositionLeft() {
		return new Coordinate(this.x - 1, this.y);
	}
	
	/**
	 * Returns the position right from the given position
	 * @return coordinate
	 */
	public Coordinate getPositionRight() {
		return new Coordinate(this.x + 1, this.y);
	}
	
	/**
	 * Returns coordinates based on the provided input.
	 * 
	 * @param input
	 * @return Coordinates
	 * @throws InvalidInputException when the input is not correct (E.g: 1 2 N)
	 */
	public static Coordinate parseCoordinates(String input) throws InvalidInputException {
		int x, y = 0;
		try {
			String[] inputs = input.split(SEPARATOR);
			x = Integer.parseInt(inputs[0]);
			y = Integer.parseInt(inputs[1]);
		} catch (Exception e) {
			throw new InvalidInputCoordinatesException();
		}
		
		if(x < 0 || y < 0) 
			throw new InvalidInputCoordinatesException();
		
		return new Coordinate(x, y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * In order to know if a Robot is in an expected position,
	 * we need to compare both Coordinate objects, 
	 * being the same whenever the coordinates are the same
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Coordinate))
			return false;
		
		return this.x == ((Coordinate) o).getX() 
				&& this.y == ((Coordinate) o).getY();
	}

	/**
	 * To fulfill to the contract, 
	 * we should override hashCode whenever overriding equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
}
