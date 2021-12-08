package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void canMoveTo() {
        GrassField map = new GrassField(15);

        assertTrue(map.canMoveTo(new Vector2d(6, 3)));
        assertTrue(map.canMoveTo(new Vector2d(-1244, 35)));
        assertTrue(map.canMoveTo(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE)));
        assertTrue(map.canMoveTo(new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));

        map.place(new Animal(map, new Vector2d(5, 3)));

        assertFalse(map.canMoveTo(new Vector2d(5, 3)));
    }

    @Test
    void place() {
        GrassField map = new GrassField(10);

        assertTrue(map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map, new Vector2d(3, 5))));
        assertTrue(map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MIN_VALUE))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(3, 5))));
    }

    @Test
    void isOccupied() {
        GrassField map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(35, 6)));
        assertTrue(map.isOccupied(new Vector2d(35, 6)));

        GrassField map2 = new GrassField(0);
        assertFalse(map2.isOccupied(new Vector2d(34, 23)));
    }

    @Test
    void objectAt() {
        GrassField map = new GrassField(10);

        Animal animal = new Animal(map, new Vector2d(3, 4));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(3, 4)));

        GrassField map2 = new GrassField(0);

        assertNull(map2.objectAt(new Vector2d(3, 4)));

        Animal animal2 = new Animal(map2, new Vector2d(3, 4));
        map2.place(animal2);
        assertEquals(animal2, map2.objectAt(new Vector2d(3, 4)));
        assertNull(map2.objectAt(new Vector2d(1, 2)));
    }
}
