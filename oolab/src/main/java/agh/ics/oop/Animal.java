package agh.ics.oop;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observersList = new ArrayList<>();

    public Animal(IWorldMap map) {
        this.map = map;
        this.addObserver(this.map);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.addObserver(this.map);
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public String toString() {
        return switch (this.direction) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> this.direction = this.direction.previous();
            case RIGHT -> this.direction = this.direction.next();
            case FORWARD -> {
                Vector2d higherPosition = this.position.add(this.direction.toUnitVector());
                if (map.canMoveTo(higherPosition)) {
                    Vector2d oldPosition = this.position;
                    positionChanged(oldPosition, higherPosition);
                    this.position = higherPosition;
                }
            }
            case BACKWARD -> {
                Vector2d lowerPosition = this.position.subtract(this.direction.toUnitVector());
                if (map.canMoveTo(lowerPosition)) {
                    Vector2d oldPosition = this.position;
                    positionChanged(oldPosition, lowerPosition);
                    this.position = lowerPosition;
                }
            }
        }
    }

    private void addObserver(IPositionChangeObserver observer) {
        this.observersList.add(observer);
    }

    private void removeObserver(IPositionChangeObserver observer) {
        this.observersList.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.observersList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String getImagePath() {
        return switch (this.direction) {
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/right.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
        };
    }

}