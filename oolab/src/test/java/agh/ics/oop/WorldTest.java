package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.MoveDirection.*;
import static agh.ics.oop.MapDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {
    @Test
    void parse() {
        String[] set1 = new String[]{"f", "forward", "r", "right", "b", "f", "left", "l"};
        String[] set2 = new String[]{"nothing", "crap", "void", "nonsense", "death"};
        String[] set3 = new String[]{"nothing", "backward", "b", "f", "crap", "FoRwArd", "r", "left", "F", "f"};
        List<MoveDirection> expected1 = new ArrayList<>(Arrays.asList(FORWARD, FORWARD, RIGHT, RIGHT, BACKWARD, FORWARD, LEFT, LEFT));
        List<MoveDirection> expected2 = new ArrayList<>(List.of());
        List<MoveDirection> expected3 = new ArrayList<>(Arrays.asList(BACKWARD, BACKWARD, FORWARD, RIGHT, LEFT, FORWARD));

        assertEquals(expected1, OptionsParser.parse(set1));
        assertEquals(expected2, OptionsParser.parse(set2));
        assertEquals(expected3, OptionsParser.parse(set3));
    }

    @Test
    void direction() {
        Animal testAnimal = new Animal();
        MoveDirection[] input = new MoveDirection[]{FORWARD, RIGHT, FORWARD, RIGHT, LEFT, LEFT, LEFT, BACKWARD, LEFT};
        MapDirection[] expected = new MapDirection[]{NORTH, EAST, EAST, SOUTH, EAST, NORTH, WEST, WEST, SOUTH};

        for (int i = 0; i < 9; i++) {
            testAnimal.move(input[i]);
            assertEquals(expected[i], testAnimal.getDirection());
        }
    }

    @Test
    void position() {
        Animal testAnimal = new Animal(new Vector2d(2, 1));
        MoveDirection[] input = new MoveDirection[]{FORWARD, RIGHT, FORWARD, RIGHT, LEFT, LEFT, LEFT, BACKWARD, LEFT, FORWARD};
        Vector2d[] expected = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(3, 2),
                new Vector2d(3, 2), new Vector2d(3, 2), new Vector2d(3, 2), new Vector2d(3, 2),
                new Vector2d(4, 2), new Vector2d(4, 2), new Vector2d(4, 1)};

        for (int i = 0; i < 10; i++) {
            testAnimal.move(input[i]);
            assertEquals(expected[i], testAnimal.getPosition());
        }
    }

    @Test
    void area() {
        Animal[] testAnimals = new Animal[]{new Animal(WEST, new Vector2d(0, 2)), new Animal(SOUTH, new Vector2d(2, 0)),
                new Animal(EAST, new Vector2d(4, 2)), new Animal(new Vector2d(2, 4))};
        Vector2d[] expected = new Vector2d[]{new Vector2d(0, 2), new Vector2d(2, 0), new Vector2d(4, 2),
                new Vector2d(2, 4)};

        for (int i = 0; i < 4; i++) {
            testAnimals[i].move(FORWARD);
            assertEquals(expected[i], testAnimals[i].getPosition());
        }
    }
}
