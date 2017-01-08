package codility.robot.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotPositionTest {
    private RobotPosition x1ByY2NorthFacing;

    @Before
    public void setUp() throws Exception {
        this.x1ByY2NorthFacing = new RobotPosition(new Coordinate(1, 2), Direction.NORTH);
    }

    @Test
    public void constructorRequiresACoordinate() throws Exception {
        try {
            new RobotPosition(null, Direction.NORTH);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("'coordinate' is required", e.getMessage());
        }
    }

    @Test
    public void constructorRequiresADirection() throws Exception {
        try {
            new RobotPosition(new Coordinate(1, 2), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("'direction' is required", e.getMessage());
        }
    }

    @Test
    public void shouldExposeXAndYAndDirection() throws Exception {
        assertEquals(new Coordinate(1, 2), x1ByY2NorthFacing.coordinate());
        assertEquals(Direction.NORTH, x1ByY2NorthFacing.direction());
    }

    @Test
    public void nextPositionForwardIsOneUnitForwardOnTheSameDirection() {
        RobotPosition newPosition = x1ByY2NorthFacing.nextPositionForward();

        assertEquals(x1ByY2NorthFacing.coordinate().x(), newPosition.coordinate().x());
        assertEquals(x1ByY2NorthFacing.coordinate().y() + 1, newPosition.coordinate().y());
        assertEquals(x1ByY2NorthFacing.direction(), newPosition.direction());
    }

    @Test
    public void nextPositionAfterRotatingLeftShouldRotate90DegreeLeftWithoutChangingXAndY() {
        RobotPosition newPosition = x1ByY2NorthFacing.nextPositionAfterRotatingLeft();

        assertEquals(x1ByY2NorthFacing.coordinate().x(), newPosition.coordinate().x());
        assertEquals(x1ByY2NorthFacing.coordinate().y(), newPosition.coordinate().y());
        assertEquals(Direction.WEST, newPosition.direction());
    }

    @Test
    public void nextPositionAfterRotatingRightShouldRotate90DegreeRightWithoutChangingXAndY() {
        RobotPosition newPosition = x1ByY2NorthFacing.nextPositionAfterRotatingRight();

        assertEquals(x1ByY2NorthFacing.coordinate().x(), newPosition.coordinate().x());
        assertEquals(x1ByY2NorthFacing.coordinate().y(), newPosition.coordinate().y());
        assertEquals(Direction.EAST, newPosition.direction());
    }
}