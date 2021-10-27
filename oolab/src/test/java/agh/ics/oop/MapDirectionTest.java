package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MapDirectionTest {
    @Test
    public void next() {
        MapDirection[] base = new MapDirection[]{MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        MapDirection[] expected = new MapDirection[]{MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST,
        MapDirection.NORTH};

        for (int i = 0; i < 4; i++) {
            assertEquals(base[i].next(), expected[i]);
        }
    }

    @Test
    public void previous() {
        MapDirection[] base = new MapDirection[]{MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        MapDirection[] expected = new MapDirection[]{MapDirection.WEST, MapDirection.NORTH, MapDirection.EAST,
        MapDirection.SOUTH};

        for (int i = 0; i < 4; i++) {
            assertEquals(base[i].previous(), expected[i]);
        }
    }
}
