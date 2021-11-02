package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(MapDirection direction) {
        this.direction = direction;
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;
        this.position = position;
    }

    public Animal(MapDirection direction, Vector2d position) {
        this.direction = direction;
        this.position = position;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString() {
        return this.position + " " + this.direction;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private boolean isInArea(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(4, 4));
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case FORWARD:
                if (isInArea(this.position.add(this.direction.toUnitVector()))) {
                    this.position = this.position.add(this.direction.toUnitVector());
                }
                break;
            case BACKWARD:
                if (isInArea(this.position.subtract(this.direction.toUnitVector()))) {
                    this.position = this.position.subtract(this.direction.toUnitVector());
                }
                break;
        }
    }
}