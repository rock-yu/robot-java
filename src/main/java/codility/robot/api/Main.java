package codility.robot.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import codility.robot.api.command.CommandHelp;
import codility.robot.api.command.PlaceCommand;

import static org.apache.commons.lang.StringUtils.trimToNull;

public class Main {

    public static void main(String[] args) throws IOException {
        RobotApplicationApi api = new RobotApplicationApi();

        api.startNewGame();
        System.out.println("Robot game started with a 5 by 5 table");
        System.out.println("Enter valid command, or 'EXIT' to quite, or 'HELP' to list valid commands:");


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String command = trimToNull(in.readLine());

            if (command != null) {
                command = command.toUpperCase();

                if ("MOVE".equals(command)) {
                    api.move();
                } else if ("LEFT".equals(command)) {
                    api.left();
                } else if ("RIGHT".equals(command)) {
                    api.right();
                } else if (command.startsWith("PLACE")) {
                    try {
                        PlaceCommand placeCommand = PlaceCommand.fromString(command);
                        api.place(placeCommand.getX(), placeCommand.getY(), placeCommand.getDirection());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid 'PLACE' command \nUsage: " + CommandHelp.HELP_PLACE.toString());
                    }
                } else if("REPORT".equals(command)) {
                    String report = api.report();
                    System.out.println(report);
                } else if ("EXIT".equals(command)) {
                    System.out.println("Bye");
                    System.exit(0);
                } else if("HELP".equals(command)) {
                    CommandHelp.printCommandsUsage();
                } else {
                    System.err.println("Invalid command: '" + command + "'");
                }
            }
        }
    }


}
