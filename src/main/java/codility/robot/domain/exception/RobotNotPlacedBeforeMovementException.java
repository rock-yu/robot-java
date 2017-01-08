package codility.robot.domain.exception;

public class RobotNotPlacedBeforeMovementException extends RuntimeException {
    public RobotNotPlacedBeforeMovementException() {
        super("Robot must be placed first before movement");
    }
}
