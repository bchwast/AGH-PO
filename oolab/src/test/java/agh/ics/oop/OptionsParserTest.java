package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.MoveDirection.*;
import static agh.ics.oop.MoveDirection.FORWARD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {
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
}
