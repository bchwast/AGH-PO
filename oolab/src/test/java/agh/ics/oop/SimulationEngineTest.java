package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationEngineTest {
    @Test
    void test1() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r",
                "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2, 2), new Vector2d(3, 4)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        List<Vector2d> expectedPositions = new ArrayList<>(List.of(new Vector2d(2, 0), new Vector2d(3, 4)));

        for (int i = 0; i < 2; i++) {
            assertEquals(expectedPositions.get(i), engine.getAnimalPositions(i));
        }
    }

    @Test
    void test2() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "r", "l", "f", "f", "b", "b",
                "l", "r", "f", "f", "b", "r", "f", "f", "f", "f", "f", "f", "r", "l", "f"});
        IWorldMap map = new RectangularMap(20, 15);
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2, 2), new Vector2d(15, 11),
                new Vector2d(12, 3)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        List<Vector2d> expectedPositions = new ArrayList<>(List.of(new Vector2d(3, 6), new Vector2d(16, 11),
                new Vector2d(13, 0)));

        for (int i = 0; i < 3; i++) {
            assertEquals(expectedPositions.get(i), engine.getAnimalPositions(i));
        }
    }

    @Test
    void test3() {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "b", "r", "r", "l", "f", "f", "b", "b",
                "l", "r", "f", "f", "b", "r", "f", "f", "f", "f", "f", "f", "r", "l", "f", "r", "f", "l", "f", "f", "f",
                "f", "f", "f", "f", "f", "f", "f", "f", "r", "l", "r"});
        IWorldMap map = new RectangularMap(30, 20);
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(15, 15)));
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertEquals(new Vector2d(15, 0), engine.getAnimalPositions(0));
    }
}
