package codility.robot.domain.model;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Representing the four cardinal directions
 */
public enum Direction {
    EAST {
        public Direction left() { return NORTH; }
        public Direction right() { return SOUTH; }
    },
    SOUTH {
        public Direction left() { return EAST;}
        public Direction right() { return WEST; }
    },
    WEST {
        public Direction left() { return SOUTH; }
        public Direction right() { return NORTH; }
    },
    NORTH {
        public Direction left() { return WEST; }
        public Direction right() { return EAST; }
    };

    public static Direction fromString(String aDirection) {
        checkArgument(aDirection != null && aDirection.length() >= 0, "A valid 'Direction' string is required");

        try {
            return Direction.valueOf(aDirection);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid direction: '" + aDirection + "'");
        }
    }

    public abstract Direction right();
    public abstract Direction left();
}
