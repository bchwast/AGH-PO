package agh.ics.oop;

import java.util.LinkedHashMap;

public abstract class AbstractWorldMap implements IWorldMap{
    protected final LinkedHashMap<Vector2d, AbstractWorldMapElement> mapElementsList = new LinkedHashMap<>();
    protected final MapVisualizer visualize = new MapVisualizer(this);
    protected Vector2d lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (! (objectAt(position) instanceof Animal));
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {

            this.mapElementsList.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapElementsList.get(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElementsList.get(position);
    }

    public String toString() {
        return this.visualize.draw(this.lowerLeftCorner, this.upperRightCorner);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement animal = mapElementsList.remove(oldPosition);
        mapElementsList.put(newPosition, animal);
    }
}
