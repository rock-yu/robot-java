package codility.robot.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotApplicationApiTest {
    private RobotApplicationApi api;

    @Before
    public void setUp() throws Exception {
        this.api = new RobotApplicationApi();
        this.api.startNewGame();
    }

    @Test
    public void testExampleA() {
        api.place(0, 0, "NORTH");
        api.move();

        String report = api.report();
        assertEquals("0,1,NORTH", report);
    }

    @Test
    public void testExampleB() {
        api.place(0, 0, "NORTH");
        api.left();

        String report = api.report();
        assertEquals("0,0,WEST", report);
    }

    @Test
    public void testExampleC() {
        api.place(1, 2, "EAST");
        api.move();
        api.move();
        api.left();
        api.move();

        String report = api.report();
        assertEquals("3,3,NORTH", report);
    }

}