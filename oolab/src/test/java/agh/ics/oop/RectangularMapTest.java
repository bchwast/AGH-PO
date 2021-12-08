package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    void canMoveTo() {
        RectangularMap map = new RectangularMap(5, 5);

        Vector2d[] goodInput = new Vector2d[]{new Vector2d(0, 0), new Vector2d(4, 4)};
        for (Vector2d pos : goodInput) {
            assertTrue(map.canMoveTo(pos));
        }

        Vector2d[] badInput = new Vector2d[]{new Vector2d(-1, -3), new Vector2d(-1, 0), new Vector2d(4, -1),
                new Vector2d(5, 3), new Vector2d(2, 6), new Vector2d(19, 23)};
        for (Vector2d pos : badInput) {
            assertFalse(map.canMoveTo(pos));
        }

        Animal animal = new Animal(map);
        map.place(animal);
        assertFalse(map.canMoveTo(animal.getPosition()));
    }

    @Test
    void place() {
        RectangularMap map = new RectangularMap(5, 5);

        assertTrue(map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map, new Vector2d(3,4))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map)));
    }

    @Test
    void isOccupied() {
        RectangularMap map = new RectangularMap(5, 5);

        assertFalse(map.isOccupied(new Vector2d(3, 4)));

        Animal animal = new Animal(map, new Vector2d(3, 4));
        map.place(animal);
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void objectAt() {
        RectangularMap map = new RectangularMap(5, 5);

        assertNull(map.objectAt(new Vector2d(3, 4)));

        Animal animal = new Animal(map, new Vector2d(3, 4));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(3, 4)));
        assertNull(map.objectAt(new Vector2d(1, 2)));
    }
}
