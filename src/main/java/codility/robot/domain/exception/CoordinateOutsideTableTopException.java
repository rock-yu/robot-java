package codility.robot.domain.exception;

import codility.robot.domain.model.Coordinate;

import static java.lang.String.format;

public class CoordinateOutsideTableTopException extends RuntimeException {
    public CoordinateOutsideTableTopException(Coordinate coordinate) {
        super(format("Coordinate '%s' is outside the table", coordinate));
    }
}
