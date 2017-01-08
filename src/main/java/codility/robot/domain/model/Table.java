package codility.robot.domain.model;

import codility.robot.domain.exception.CoordinateOutsideTableTopException;

import static com.google.common.base.Preconditions.checkArgument;

// rectangular table surface
public class Table implements Surface {
    private Coordinate lowerCoordinate;
    private Coordinate higherCoordinate;

    public Table(Coordinate aLowerCoordinate, Coordinate aHigherCoordinate) {
        checkArgument(aLowerCoordinate != null);
        checkArgument(aHigherCoordinate != null);

        checkArgument((aLowerCoordinate.x() < aHigherCoordinate.x()) && aLowerCoordinate.y() < aHigherCoordinate.y(),
                "Fail to form a rectangular table using coordinates [%s, %s]", aLowerCoordinate, aHigherCoordinate);

        this.lowerCoordinate = aLowerCoordinate;
        this.higherCoordinate = aHigherCoordinate;
    }

    @Override
    public void ensureCoordinateIsOnSurface(Coordinate coordinate) {
        checkArgument(coordinate != null, "'coordinate' is required");

        boolean withinBoundary =
                (coordinate.x() >= lowerCoordinate.x()) && (coordinate.x() <= higherCoordinate.x())
                && (coordinate.y() >= lowerCoordinate.y()) && (coordinate.y() <= higherCoordinate.y());

        if (!withinBoundary) {
            throw new CoordinateOutsideTableTopException(coordinate);
        }
    }

}
