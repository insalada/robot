package com.ipbsoft.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.ipbsoft.robot.beans.Robot;
import org.junit.Before;
import org.junit.Test;

import com.ipbsoft.robot.beans.Coordinate;
import com.ipbsoft.robot.beans.Move;
import com.ipbsoft.robot.beans.Plateau;
import com.ipbsoft.robot.enums.Orientation;
import com.ipbsoft.robot.exceptions.InvalidInputException;
import com.ipbsoft.robot.exceptions.InvalidInputMoveException;
import com.ipbsoft.robot.exceptions.InvalidInputOrientationException;
import com.ipbsoft.robot.exceptions.NoRobotsDeployedException;

/**
 * Some tests to check the code is valid.
 * Some of then are part of TDD methodology
 * @author insalada
 *
 */
public class RobotTest {

	private Plateau plateauMock;
	private Robot robotMock;

	@Before
	public void setUp() throws Exception {
		plateauMock = new Plateau(new Coordinate(5, 5));
		robotMock = new Robot(Orientation.N);
	}

	@Test(expected = InvalidInputOrientationException.class)
	public void should_throw_InvalidOrientationException() throws InvalidInputOrientationException {
		Orientation.lookup("A");
	}

	@Test
	public void should_lookup_orientation() throws InvalidInputOrientationException {
		Orientation orientation = Orientation.lookup("N");
		assertEquals(Orientation.N, orientation);
	}

	@Test
	public void should_parse_orientation_when_input_is_correct() throws InvalidInputException {
		assertEquals(Orientation.N, Orientation.parseOrientation("1 2 N"));
	}

	@Test(expected = InvalidInputException.class)
	public void should_parsePosition_throw_InvalidInputException_when_input_is_wrong() throws InvalidInputException {
		Coordinate.parseCoordinates("A B C");
	}

	@Test
	public void should_parse_position_when_input_is_correct() throws InvalidInputException {
		Coordinate coordinate = Coordinate.parseCoordinates("1 2");
		assertNotNull(coordinate);
	}

	@Test(expected = InvalidInputException.class)
	public void should_parse_orientation_throw_InvalidInputException_when_input_is_wrong()
			throws InvalidInputException {
		Orientation.parseOrientation("1 2");
	}

	@Test(expected = InvalidInputOrientationException.class)
	public void should_parse_orientation_throw_InvalidInputOrientationException_when_input_is_wrong()
			throws InvalidInputException {
		Orientation.parseOrientation("1 2 A");
	}
	
	@Test
	public void should_pull_the_first_robot_from_the_queue() throws NoRobotsDeployedException {
		Robot robot1 = new Robot(Orientation.N);
		Robot robot2 = new Robot(Orientation.S);
		plateauMock.addRobot(robot1, new Coordinate(1, 1));
		plateauMock.addRobot(robot2, new Coordinate(2, 2));
		assertEquals(robot1, plateauMock.pullRobot());
	}
	
	@Test(expected=NoRobotsDeployedException.class)
	public void should_throw_NoRobotsDeployedException_when_no_robots_available() throws NoRobotsDeployedException {
		plateauMock.pullRobot();
	}

	@Test
	public void should_pass_place_robot_validation_when_input_is_correct() throws InvalidInputException {
		// set plateau limits
		Coordinate plateauLimits = Coordinate.parseCoordinates("5 5");
		Plateau plateau = new Plateau(plateauLimits);

		// validates Robot position into the plateau
		Coordinate robotPosition = Coordinate.parseCoordinates("1 2 N");
		plateau.validate(robotPosition);
	}

	@Test(expected = InvalidInputException.class)
	public void should_not_pass_place_robot_validation_when_input_is_correct() throws InvalidInputException {
		// set plateau limits
		Coordinate plateauBounds = Coordinate.parseCoordinates("5 5");
		Plateau plateau = new Plateau(plateauBounds);

		// validates Robot position into the plateau
		Coordinate robotPosition = Coordinate.parseCoordinates("6 5 N");
		plateau.validate(robotPosition);
	}
	
	@Test
	public void should_validate_move_when_input_is_correct() throws InvalidInputMoveException {
		String input = "LMRMM";
		Move.validate(input);
	}

	@Test(expected = InvalidInputMoveException.class)
	public void should_throw_InvalidInputMoveException_move_when_input_is_wrong() throws InvalidInputMoveException {
		String input = "ABC";
		Move.validate(input);
	}

	@Test
	public void should_turn_left_returns_previous_orientation() {
		Orientation actual = Orientation.turnLeft(Orientation.W);
		assertEquals(Orientation.S, actual);
	}

	@Test
	public void should_turn_right_returns_next_orientation() {
		Orientation actual = Orientation.turnRight(Orientation.N);
		assertEquals(Orientation.E, actual);
	}

	@Test
	public void should_turn_left_returns_previous_orientation_circularly() {
		Orientation actual = Orientation.turnLeft(Orientation.N);
		assertEquals(Orientation.W, actual);
	}

	@Test
	public void should_turn_right_returns_next_orientation_circularly() {
		Orientation actual = Orientation.turnRight(Orientation.W);
		assertEquals(Orientation.N, actual);
	}
	
	@Test
	public void should_advance_move_forward() throws InvalidInputException {
		plateauMock.addRobot(robotMock, new Coordinate(1, 2));
		Move move = new Move(plateauMock, robotMock, "M");
		move.processMoves();
		assertEquals("1 3 N", move.output());
	}

	@Test
	public void should_process_moves_return_expected_output() {
		plateauMock.addRobot(robotMock, new Coordinate(1, 2));
		Move move = new Move(plateauMock, robotMock, "LMLMLMLMM");
		move.processMoves();
		assertEquals("1 3 N", move.output());
	}
}