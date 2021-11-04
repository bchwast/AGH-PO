package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    static public List<MoveDirection> parse(String[] input) {
        List<MoveDirection> output = new ArrayList<>();

        for (String arg : input) {
            String mess = switch(arg) {
                case "f", "forward" -> "FORWARD";
                case "b", "backward" -> "BACKWARD";
                case "l", "left" -> "LEFT";
                case "r", "right" -> "RIGHT";
                default -> "pass";
            };

            if (! mess.equals("pass")) {
                output.add(MoveDirection.valueOf(mess));
            }
        }

        return output;
    }
}
