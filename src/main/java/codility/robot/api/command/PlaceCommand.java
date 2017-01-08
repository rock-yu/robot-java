package codility.robot.api.command;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNumeric;

public class PlaceCommand {
    private int x;
    private int y;
    private String direction;

    private static String PREFIX = "PLACE ";

    private PlaceCommand(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    // PLACE 0,0,NORTH
    public static PlaceCommand fromString(String command) {
        checkArgument(command != null && !command.isEmpty());

        String trimmedCommand = command.trim().toUpperCase();
        checkArgument(trimmedCommand.startsWith(PREFIX) && trimmedCommand.length() >= PREFIX.length());

        String parameter = trimmedCommand.substring(PREFIX.length(), trimmedCommand.length());
        String[] tokens = parameter.split(",");

        checkArgument(tokens.length == 3);
        checkArgument(isNumeric(tokens[0]) && isNumeric(tokens[1]) && isValidDirection(tokens[2]));

        return new PlaceCommand(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2]);
    }

    private static boolean isValidDirection(String token) {
        return "EAST".equals(token) || "SOUTH".equals(token) || "WEST".equals(token) || "NORTH".equals(token);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }
}
