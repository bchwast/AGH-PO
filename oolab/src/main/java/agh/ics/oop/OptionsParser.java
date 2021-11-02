package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    private final String[] input;

    public OptionsParser(String[] input){
        this.input = input;
    }

    public List<MoveDirection> parse() {
        List<MoveDirection> output = new ArrayList<>();

        for (String arg : this.input) {
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
