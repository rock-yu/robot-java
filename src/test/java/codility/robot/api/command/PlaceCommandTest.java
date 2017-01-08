package codility.robot.api.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceCommandTest {
    @Test
    public void fromStringWithValidPlaceCommand() throws Exception {
        PlaceCommand placeCommand = PlaceCommand.fromString("PLACE 1,2,NORTH");
        assertEquals(1, placeCommand.getX());
        assertEquals(2, placeCommand.getY());
        assertEquals("NORTH", placeCommand.getDirection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringWithInvalidX() throws Exception {
        PlaceCommand placeCommand = PlaceCommand.fromString("PLACE A,2,NORTH");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringWithInvalidY() throws Exception {
        PlaceCommand placeCommand = PlaceCommand.fromString("PLACE 1,A,NORTH");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringWithInvalidDirection() throws Exception {
        PlaceCommand placeCommand = PlaceCommand.fromString("PLACE 1,2,UNKNOWN");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromStringWithInvalidPrefix() throws Exception {
        PlaceCommand placeCommand = PlaceCommand.fromString("UNKNOWN 1,2,NORTH");
    }
}