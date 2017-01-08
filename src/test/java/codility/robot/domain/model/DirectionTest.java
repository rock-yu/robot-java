package codility.robot.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {
    @Test
    public void canTranslateValidStringToDirectionEnum() throws Exception {
        assertEquals(Direction.NORTH, Direction.fromString("NORTH"));
        assertEquals(Direction.SOUTH, Direction.fromString("SOUTH"));
    }

    @Test
    public void findTheNextDirection() throws Exception {
        assertEquals(Direction.NORTH, Direction.EAST.left());
        assertEquals(Direction.SOUTH, Direction.EAST.right());

        assertEquals(Direction.EAST, Direction.SOUTH.left());
        assertEquals(Direction.WEST, Direction.SOUTH.right());

        assertEquals(Direction.SOUTH, Direction.WEST.left());
        assertEquals(Direction.NORTH, Direction.WEST.right());

        assertEquals(Direction.WEST, Direction.NORTH.left());
        assertEquals(Direction.EAST, Direction.NORTH.right());
    }

    @Test
    public void canNotTranslateNullToDirectionEnum() throws Exception {
        try {
            Direction.fromString(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("A valid 'Direction' string is required", e.getMessage());
        }
    }

    @Test
    public void canNotTranslateInvalidStringToDirectionEnum() throws Exception {
        try {
            String invalidDirectionString = "invalid-direction";
            Direction.fromString(invalidDirectionString);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid direction: 'invalid-direction'", e.getMessage());
        }
    }

}