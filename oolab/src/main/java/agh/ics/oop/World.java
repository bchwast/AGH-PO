package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


public class World{
    public static void run(List<Direction> args){
        for (Direction arg : args){
            String mess = switch (arg){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case LEFT -> "Zwierzak skręca w lewo";
                case RIGHT -> "Zwierzak skręca w prawo";
            };

            out.println(mess);
        }
    }

    public static List<Direction> translate(String[] input){
        List<Direction> output = new ArrayList<>();

        for (String arg : input){
            String mess = switch(arg){
                case "f" -> "FORWARD";
                case "b" -> "BACKWARD";
                case "r" -> "RIGHT";
                case "l" -> "LEFT";
                default -> "pass";
            };

            if (! mess.equals("pass")) {
                output.add(Direction.valueOf(mess));
            }
        }

        return output;
    }

    public static void main(String[] args){
        try {
            out.println("Start");
            Application.launch(App.class, args);

            out.println("Stop");
        }
        catch(IllegalArgumentException ex) {
            out.println("An error has occured:\n" + ex);
        }
    }
}