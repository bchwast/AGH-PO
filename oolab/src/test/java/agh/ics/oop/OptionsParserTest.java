package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.MoveDirection.*;
import static agh.ics.oop.MoveDirection.FORWARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsParserTest {
    @Test
    void parse() {
        String[] set1 = new String[]{"f", "forward", "r", "right", "b", "f", "left", "l"};
        String[] set2 = new String[]{"nothing", "crap", "void", "nonsense", "death"};
        String[] set3 = new String[]{"nothing", "backward", "b", "f", "crap", "FoRwArd", "r", "left", "F", "f"};
        List<MoveDirection> expected1 = new ArrayList<>(Arrays.asList(FORWARD, FORWARD, RIGHT, RIGHT, BACKWARD, FORWARD, LEFT, LEFT));

        assertEquals(expected1, OptionsParser.parse(set1));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(set2));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(set3));
    }
}
