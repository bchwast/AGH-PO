package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
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
                    this.position = higherPosition;
                }
            }
            case BACKWARD -> {
                Vector2d lowerPosition = this.position.subtract(this.direction.toUnitVector());
                if (map.canMoveTo(lowerPosition)) {
                    this.position = lowerPosition;
                }
            }
        }
    }
}