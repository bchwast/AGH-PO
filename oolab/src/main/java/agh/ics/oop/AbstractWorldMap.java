package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected final List<AbstractWorldMapElement> mapElementsList = new ArrayList<>();
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
            this.mapElementsList.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (AbstractWorldMapElement element : mapElementsList) {
            if (element.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (AbstractWorldMapElement element : mapElementsList) {
            if (element.getPosition().equals(position)) {
                return element;
            }
        }
        return null;
    }

    public String toString() {
        return this.visualize.draw(this.lowerLeftCorner, this.upperRightCorner);
    }
}
