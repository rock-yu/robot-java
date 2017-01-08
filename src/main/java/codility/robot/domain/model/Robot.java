package codility.robot.domain.model;

import codility.robot.domain.exception.RobotNotPlacedBeforeMovementException;
import com.google.common.base.Verify;

import static com.google.common.base.Preconditions.checkArgument;

public class Robot {

    private RobotPosition position;

    public boolean isPlaced() {
        return (this.position != null);
    }

    public RobotPosition currentPosition() {
        return this.position;
    }

    public void placeOnTable(Surface surface, Coordinate aCoordinate, Direction aDirection) {
        checkArgument(surface != null, "'surface' is required when placing");
        checkArgument(aCoordinate != null, "'Coordinate' is required when placing");
        checkArgument(aDirection != null, "'Direction' is required when placing");

        this.internalUpdatePosition(surface, new RobotPosition(aCoordinate, aDirection));
    }

    public void moveOneUnitForward(Surface surface) {
        this.ensureRobotIsPlaced();

        RobotPosition newPosition = this.position.nextPositionForward();
        this.internalUpdatePosition(surface, newPosition);
    }

    public void rotateLeft(Surface surface) {
        this.ensureRobotIsPlaced();

        RobotPosition newPosition = this.position.nextPositionAfterRotatingLeft();
        this.internalUpdatePosition(surface, newPosition);
    }

    public void rotateRight(Surface surface) {
        this.ensureRobotIsPlaced();

        RobotPosition newPosition = this.position.nextPositionAfterRotatingRight();
        this.internalUpdatePosition(surface, newPosition);
    }

    // internal
    private void ensureRobotIsPlaced() {
        if (!isPlaced()) {
            throw new RobotNotPlacedBeforeMovementException();
        }
    }

    private void internalUpdatePosition(Surface surface, RobotPosition newPosition) {
        Verify.verifyNotNull(newPosition);

        surface.ensureCoordinateIsOnSurface(newPosition.coordinate());
        this.position = newPosition;
    }
}
