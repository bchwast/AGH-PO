package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void equals() {
        assertEquals(new Vector2d(4, 4), new Vector2d(4, 4));
        assertNotEquals(new Vector2d(4, 4), new Vector2d(4, 2));
        assertNotEquals(new Vector2d(4, 4), new int[]{4, 4});
    }

    @Test
    public void testToString() {
        assertEquals("(24,11)", (new Vector2d(24, 11).toString()));
        assertNotEquals("something", new Vector2d(213, 42).toString());
    }

    @Test
    public void precedes() {
        Vector2d template = new Vector2d(3, 3);
        assertTrue(new Vector2d(1, 1).precedes(template));
        assertTrue(new Vector2d(1, 3).precedes(template));
        assertTrue(new Vector2d(3, 1).precedes(template));
        assertTrue(new Vector2d(3, 3).precedes(template));
        assertFalse(new Vector2d(4, 3).precedes(template));
        assertFalse(new Vector2d(3, 4).precedes(template));
        assertFalse(new Vector2d(4, 4).precedes(template));
    }

    @Test
    public void follows() {
        Vector2d template = new Vector2d(3, 3);
        assertTrue(new Vector2d(4, 4).follows(template));
        assertTrue(new Vector2d(4, 3).follows(template));
        assertTrue(new Vector2d(3, 4).follows(template));
        assertTrue(new Vector2d(3, 3).follows(template));
        assertFalse(new Vector2d(2, 3).follows(template));
        assertFalse(new Vector2d(3, 2).follows(template));
        assertFalse(new Vector2d(2, 2).follows(template));
    }

    @Test
    public void upperRight() {
        assertEquals(new Vector2d(1, 9), new Vector2d(1, 6).upperRight(new Vector2d(-3, 9)));
    }

    @Test
    public void lowerLeft() {
        assertEquals(new Vector2d(-3, 6), new Vector2d(1, 6).lowerLeft(new Vector2d(-3, 9)));
    }

    @Test
    public void add() {
        assertEquals(new Vector2d(11, -1), new Vector2d(4, 2).add(new Vector2d(7, -3)));
    }

    @Test
    public void subtract() {
        assertEquals(new Vector2d(-3, 5), new Vector2d(4, 2).subtract(new Vector2d(7, -3)));
    }

    @Test
    public void opposite() {
        assertEquals(new Vector2d(-21, 15), new Vector2d(21, -15).opposite());
    }
}
