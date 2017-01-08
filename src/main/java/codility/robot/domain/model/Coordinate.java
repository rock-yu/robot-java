package codility.robot.domain.model;

// immutable
// x, y coordinate
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Coordinate coordinateToTheRight() {
        return new Coordinate(this.x + 1, this.y);
    }

    public Coordinate coordinateToTheLeft() {
        return new Coordinate(this.x - 1, this.y);
    }

    public Coordinate coordinateUp() {
        return new Coordinate(this.x, this.y + 1);
    }

    public Coordinate coordinateDown() {
        return new Coordinate(this.x, this.y - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
