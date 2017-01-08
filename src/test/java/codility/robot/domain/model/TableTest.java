package codility.robot.domain.model;

import codility.robot.domain.exception.CoordinateOutsideTableTopException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TableTest {
    private Table testInstance;

    @Before
    public void setUp() throws Exception {
        this.testInstance = new Table(new Coordinate(0, 0), new Coordinate(5, 5));
    }

    @Test
    public void constructorShouldOnlyAcceptCoordinatesWhichFormsARectangularTable() throws Exception {
        // it's a line but not table
        assertExceptionIsRaisedIfAttemptToConstructTable(
                new Coordinate(0, 0), new Coordinate(5, 0), "Fail to form a rectangular table using coordinates [(0,0), (5,0)]");

        // it's a line but not table
        assertExceptionIsRaisedIfAttemptToConstructTable(
                new Coordinate(0, 0), new Coordinate(0, 0), "Fail to form a rectangular table using coordinates [(0,0), (0,0)]");

        // lower on the higher end
        assertExceptionIsRaisedIfAttemptToConstructTable(
                new Coordinate(5, 0), new Coordinate(0, 0), "Fail to form a rectangular table using coordinates [(5,0), (0,0)]");
    }

    @Test
    public void placeAnCoordinateWithinTableOrOnTheBoundaryIsAllowed() throws Exception {
        this.testInstance.ensureCoordinateIsOnSurface(new Coordinate(0, 0));
        this.testInstance.ensureCoordinateIsOnSurface(new Coordinate(1, 1));
        this.testInstance.ensureCoordinateIsOnSurface(new Coordinate(5, 0));
        this.testInstance.ensureCoordinateIsOnSurface(new Coordinate(0, 5));
        this.testInstance.ensureCoordinateIsOnSurface(new Coordinate(5, 5));
    }

    @Test
    public void placeAnCoordinateOutsideTableShouldBePrevented() throws Exception {
        // violate x
        assertExceptionIsRaisedIfAttemptToPlacedAt(new Coordinate(6, 0));

        // violate y
        assertExceptionIsRaisedIfAttemptToPlacedAt(new Coordinate(0, 6));

        // violate x and y
        assertExceptionIsRaisedIfAttemptToPlacedAt(new Coordinate(6, 6));
        assertExceptionIsRaisedIfAttemptToPlacedAt(new Coordinate(-1, -1));
    }

    // internal
    private void assertExceptionIsRaisedIfAttemptToConstructTable(Coordinate aLowerCoordinate, Coordinate aHigherCoordinate, String expectedMessage) {
        try {
            new Table(aLowerCoordinate, aHigherCoordinate);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    private void assertExceptionIsRaisedIfAttemptToPlacedAt(Coordinate coordinate) {
        try {
            this.testInstance.ensureCoordinateIsOnSurface(coordinate);
            fail();
        } catch (CoordinateOutsideTableTopException e) {
            // expected
        }
    }
}