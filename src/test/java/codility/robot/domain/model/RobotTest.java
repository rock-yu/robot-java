package codility.robot.domain.model;

import codility.robot.domain.exception.CoordinateOutsideTableTopException;
import codility.robot.domain.exception.RobotNotPlacedBeforeMovementException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTest {
    private Robot testInstance;

    private Table table10By10;

    private Coordinate x1y2 = new Coordinate(1, 2);
    private RobotPosition x1y3NorthFacing = new RobotPosition(new Coordinate(1, 3), Direction.NORTH);

    private Coordinate coordinateOutsideTable = new Coordinate(11, 2);

    @Before
    public void setUp() throws Exception {
        this.table10By10 = new Table(new Coordinate(0, 0), new Coordinate(10, 10));
        this.testInstance = new Robot();
    }

    @Test
    public void placeRobotWithinTableShouldUpdateRobotPosition() throws Exception {
        testInstance.placeOnTable(table10By10, x1y2, Direction.SOUTH);

        assertEquals(x1y2, testInstance.currentPosition().coordinate());
        assertEquals(Direction.SOUTH, testInstance.currentPosition().direction());
    }

    @Test
    public void shouldRaiseExceptionIfPlaceRobotOutsideTable() throws Exception {
        try {
            testInstance.placeOnTable(table10By10, coordinateOutsideTable, Direction.SOUTH);
            fail();
        } catch (CoordinateOutsideTableTopException e) {
            assertEquals("Coordinate '(11,2)' is outside the table", e.getMessage());
        }
    }

    @Test
    public void shouldNotChangeRobotPositionIfAttemptToPlaceOutsideTableFailed() throws Exception {
        RobotPosition robotPositionBeforeTheAttempt = testInstance.currentPosition();

        try {
            testInstance.placeOnTable(table10By10, coordinateOutsideTable, Direction.SOUTH);
            fail();
        } catch (CoordinateOutsideTableTopException e) {
            // expected
        }

        assertEquals(robotPositionBeforeTheAttempt, testInstance.currentPosition());
    }

    @Test
    public void moveOneUnitForwardShouldNotBeAllowedIfRobotNotPlaced() throws Exception {
        guardedAssertRobotIsNotPlaced();

        try {
            testInstance.moveOneUnitForward(table10By10);
            fail();
        } catch (RobotNotPlacedBeforeMovementException e) {
            // expected
        }
    }

    @Test
    public void moveOneUnitForwardIfRobotIsPlacedAndNewPositionIsStillWithinTable() throws Exception {
        givenRobotIsPlacedAtX1Y2NorthFacing();

        testInstance.moveOneUnitForward(table10By10);

        RobotPosition expectedNextPositionForwardOnTheSameDirection = x1y3NorthFacing;

        assertEquals(expectedNextPositionForwardOnTheSameDirection, testInstance.currentPosition());
    }

    @Test
    public void rotateLeftShouldNotBeAllowedIfRobotNotPlaced() throws Exception {
        guardedAssertRobotIsNotPlaced();

        try {
            testInstance.rotateLeft(table10By10);
            fail();
        } catch (RobotNotPlacedBeforeMovementException e) {
            // expected
        }
    }

    @Test
    public void rotateLeftChangeDirectionToTheLeftWithoutChangingPosition() throws Exception {
        givenRobotIsPlacedAtX1Y2NorthFacing();

        testInstance.rotateLeft(table10By10);

        assertEquals(x1y2, testInstance.currentPosition().coordinate());
        assertEquals(Direction.WEST, testInstance.currentPosition().direction());
    }

    @Test
    public void rotateRightShouldNotBeAllowedIfRobotNotPlaced() throws Exception {
        guardedAssertRobotIsNotPlaced();

        try {
            testInstance.rotateRight(table10By10);
            fail();
        } catch (RobotNotPlacedBeforeMovementException e) {
            // expected
        }
    }

    @Test
    public void rotateRightChangeDirectionToTheRightWithoutChangingPosition() throws Exception {
        givenRobotIsPlacedAtX1Y2NorthFacing();

        testInstance.rotateRight(table10By10);

        assertEquals(x1y2, testInstance.currentPosition().coordinate());
        assertEquals(Direction.EAST, testInstance.currentPosition().direction());
    }

    @Test
    public void currentPositionShouldReturnNullIfNotPlaced() throws Exception {
        guardedAssertRobotIsNotPlaced();

        assertEquals(null, testInstance.currentPosition());
    }

    @Test
    public void shouldReturnCurrentPositionIfPlaced() throws Exception {
        givenRobotIsPlacedAtX1Y2NorthFacing();

        testInstance.moveOneUnitForward(table10By10);

        Coordinate expectedNewCoordinate = new Coordinate(1, 3);
        Direction expectedDirection = Direction.NORTH;

        assertEquals(expectedNewCoordinate, testInstance.currentPosition().coordinate());
        assertEquals(expectedDirection, testInstance.currentPosition().direction());
    }

    // internal
    private void guardedAssertRobotIsNotPlaced() {
        assertTrue(!testInstance.isPlaced());
    }

    private void givenRobotIsPlacedAtX1Y2NorthFacing() {
        testInstance.placeOnTable(table10By10, x1y2, Direction.NORTH);
    }

}