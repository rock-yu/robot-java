package codility.robot.domain.model;

import static com.google.common.base.Preconditions.checkArgument;

// robot's position on a table surface which include it's coordinates and a direction. Immutable
public class RobotPosition {

    private Coordinate coordinate;
    private Direction direction;

    protected RobotPosition(Coordinate coordinate, Direction direction) {
        checkArgument(coordinate != null, "'coordinate' is required");
        checkArgument(direction != null, "'direction' is required");

        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Coordinate coordinate() {
        return coordinate;
    }

    public Direction direction() {
        return direction;
    }

    public RobotPosition nextPositionForward() {
        Coordinate newCoordinate =
                (this.direction == Direction.EAST) ? this.coordinate.coordinateToTheRight()
                        : (this.direction == Direction.SOUTH) ? this.coordinate.coordinateDown()
                        : (this.direction == Direction.WEST) ? this.coordinate.coordinateToTheLeft()
                        : this.coordinate.coordinateUp();

        return new RobotPosition(newCoordinate, this.direction);
    }

    public RobotPosition nextPositionAfterRotatingLeft() {
        return new RobotPosition(this.coordinate, this.direction.left());
    }

    public RobotPosition nextPositionAfterRotatingRight() {
        return new RobotPosition(this.coordinate, this.direction.right());
    }

    @Override
    public String toString() {
        return this.coordinate.toString() + " Facing: " + direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RobotPosition position = (RobotPosition) o;

        if (coordinate != null ? !coordinate.equals(position.coordinate) : position.coordinate != null) return false;
        return direction == position.direction;
    }

    @Override
    public int hashCode() {
        int result = coordinate != null ? coordinate.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }
}
