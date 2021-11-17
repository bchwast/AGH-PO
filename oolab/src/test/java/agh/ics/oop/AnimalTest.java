package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static agh.ics.oop.MapDirection.*;
import static agh.ics.oop.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    void direction() {
        Animal testAnimal = new Animal(new RectangularMap(5, 5));
        String[] input = new String[]{"f", "forwrd", "r", "forward", "r", "R", "left", "l", "fr", "l", "b", "L", "l"};
        List<MoveDirection> parsedInput = OptionsParser.parse(input);
        MapDirection[] expected = new MapDirection[]{NORTH, EAST, EAST, SOUTH, EAST, NORTH, WEST, WEST, SOUTH};

        for (int i = 0; i < 9; i++) {
            testAnimal.move(parsedInput.get(i));
            assertEquals(expected[i], testAnimal.getDirection());
        }
    }

    @Test
    void position() {
        Animal testAnimal = new Animal(new RectangularMap(5, 5), new Vector2d(2, 1));
        String[] input = new String[]{"f", "R", "r", "f", "r", "lEeft", "l", "rght", "l", "left", "b", "B", "l", "forward"};
        List<MoveDirection> parsedInput = OptionsParser.parse(input);
        Vector2d[] expected = new Vector2d[]{new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(3, 2),
                new Vector2d(3, 2), new Vector2d(3, 2), new Vector2d(3, 2), new Vector2d(3, 2),
                new Vector2d(4, 2), new Vector2d(4, 2), new Vector2d(4, 1)};

        for (int i = 0; i < 10; i++) {
            testAnimal.move(parsedInput.get(i));
            assertEquals(expected[i], testAnimal.getPosition());
        }
    }

    @Test
    void area() {
        Animal[] testAnimals = new Animal[]{new Animal(new RectangularMap(5, 5), new Vector2d(0, 2)),
                new Animal(new RectangularMap(5, 5), new Vector2d(2, 0)),
                new Animal(new RectangularMap(5, 5), new Vector2d(4, 2)),
                new Animal(new RectangularMap(5, 5), new Vector2d(2, 4))};

        testAnimals[0].move(LEFT);
        testAnimals[1].move(LEFT);
        testAnimals[1].move(LEFT);
        testAnimals[2].move(RIGHT);

        Vector2d[] expected = new Vector2d[]{new Vector2d(0, 2), new Vector2d(2, 0), new Vector2d(4, 2),
                new Vector2d(2, 4)};

        for (int i = 0; i < 4; i++) {
            testAnimals[i].move(FORWARD);
            assertEquals(expected[i], testAnimals[i].getPosition());
        }
    }
}
