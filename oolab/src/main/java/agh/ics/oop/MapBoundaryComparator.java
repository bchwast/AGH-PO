package agh.ics.oop;

import java.util.Comparator;
import java.util.Vector;

public class MapBoundaryComparator implements Comparator<Vector2d> {
    private final String axis;

    public MapBoundaryComparator(String axis) {
        this.axis = axis;
    }

    public int compare (Vector2d pos1, Vector2d pos2) {
        if (axis.equals("x")) {
            if (pos1.x == pos2.x) {
                return Integer.compare(pos1.y, pos2.y);
            } else {
                return Integer.compare(pos1.x, pos2.x);
            }
        }
        else {
            if (pos1.y == pos2.y) {
                return Integer.compare(pos1.x, pos2.x);
            }
            else {
                return Integer.compare(pos1.y, pos2.y);
            }
        }
    }
}
