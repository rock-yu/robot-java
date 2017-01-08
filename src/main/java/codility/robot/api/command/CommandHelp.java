package codility.robot.api.command;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.rightPad;

public class CommandHelp {

    public final static CommandHelp HELP_PLACE = new CommandHelp("PLACE X,Y,F", "Place robot at (X,Y) and facing F(EAST|SOUTH|WEST|NORTH), e.g: 'PLACE 1,2,NORTH'");
    public final static CommandHelp HELP_MOVE = new CommandHelp("MOVE", "Move the toy robot one unit forward in the direction it is currently facing");
    public final static CommandHelp HELP_LEFT = new CommandHelp("RIGHT", "Rotate the robot 90 degrees to the left");
    public final static CommandHelp HELP_RIGHT = new CommandHelp("LEFT", "Rotate the robot 90 degrees to the right");
    public final static CommandHelp HELP_REPORT = new CommandHelp("REPORT", "Announce X,Y and F of the robot");


    public static String allHelpInfo() {
        return HELP_PLACE.toString() + "\n"
                + HELP_MOVE.toString() + "\n"
                + HELP_LEFT.toString() + "\n"
                + HELP_RIGHT.toString() + "\n"
                + HELP_REPORT.toString();
    }

    public static void printCommandsUsage() {
        System.out.println("Enter the following command to control the robot, Usage: \n"  + allHelpInfo());
    }

    private String command;
    private String helpInfo;

    public CommandHelp(String command, String helpInfo) {
        this.command = command;
        this.helpInfo = helpInfo;
    }

    @Override
    public String toString() {
        return rightPad("   " + command, 16, " ") + helpInfo;
    }
}
