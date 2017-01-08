package codility.robot.api;

import codility.robot.domain.model.Coordinate;
import codility.robot.domain.model.*;
import codility.robot.domain.model.Robot;
import codility.robot.domain.model.RobotPosition;

import static com.google.common.base.Preconditions.checkState;

public class RobotApplicationApi {
    private Robot robot;
    private Surface surface;

    public void startNewGame() {
        this.robot = new Robot();
        this.surface = new Table(new Coordinate(0, 0), new Coordinate(5, 5));
    }

    public void place(int x, int y, String facing) {
        try {
            Direction direction = Direction.fromString(facing);
            requiredRobot().placeOnTable(surface, new Coordinate(x, y), direction);

            printOutOnSuccess("PLACE " + x + "," + y + "," + direction);
        } catch (Exception e) {
            printOutOnFailure("PLACE", e);
        }
    }

    public void move() {
        try {
            requiredRobot().moveOneUnitForward(surface);
            printOutOnSuccess("MOVE");
        } catch (Exception e) {
            printOutOnFailure("MOVE", e);
        }
    }

    public void left() {
        try {
            requiredRobot().rotateLeft(surface);
            printOutOnSuccess("LEFT");
        } catch (Exception e) {
            printOutOnFailure("LEFT", e);
        }
    }

    public void right() {
        try {
            requiredRobot().rotateRight(surface);
            printOutOnSuccess("RIGHT");
        } catch (Exception e) {
            printOutOnFailure("RIGHT", e);
        }
    }

    public String report() {
        RobotPosition position = robot.currentPosition();
        return (position == null) ? null : position.coordinate().x() + "," + position.coordinate().y() + "," + position.direction().toString();
    }

    // internal
    private Robot requiredRobot() {
        checkState(robot != null, "Robot not initialized, please start game first");
        return robot;
    }

    private void printOutOnSuccess(String command) {
        System.out.println("'" + command + "' done, currently at: " + report());
    }

    private void printOutOnFailure(String command, Exception e) {
        System.err.println("'" + command + "' failed, currently at: " + report() + ", error: " + e.getMessage());
    }

}
