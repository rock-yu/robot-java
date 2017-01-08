package codility.robot.domain.model;

// 2-dimensional surface on which a robot can roam around
public interface Surface {
    void ensureCoordinateIsOnSurface(Coordinate coordinate);
}
