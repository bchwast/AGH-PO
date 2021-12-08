package agh.ics.oop;

import java.util.TreeMap;


public class MapBoundary implements IPositionChangeObserver{
    private final TreeMap<Vector2d, IMapElement> mapElementsX = new TreeMap<Vector2d, IMapElement>(new MapBoundaryComparator("x"));
    private final TreeMap<Vector2d, IMapElement> mapElementsY = new TreeMap<Vector2d, IMapElement>(new MapBoundaryComparator("y"));

    public void addElement(IMapElement element) {
        this.mapElementsX.put(element.getPosition(), element);
        this.mapElementsY.put(element.getPosition(), element);
    }

    public void removeElement(IMapElement element) {
        this.mapElementsX.remove(element.getPosition());
        this.mapElementsY.remove(element.getPosition());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = this.mapElementsX.get(oldPosition);

        this.mapElementsX.remove(oldPosition);
        this.mapElementsY.remove(oldPosition);
        this.mapElementsX.put(newPosition, element);
        this.mapElementsY.put(newPosition, element);
    }

    public Vector2d getLowerLeft() {
        if (this.mapElementsX.size() == 0) {
            return new Vector2d(0, 0);
        }

        return this.mapElementsX.firstKey().lowerLeft(this.mapElementsY.firstKey());
    }

    public Vector2d getUpperRight() {
        if (this.mapElementsY.size() == 0) {
            return new Vector2d(0, 0);
        }

        return this.mapElementsX.lastKey().upperRight(this.mapElementsY.lastKey());
    }
}
