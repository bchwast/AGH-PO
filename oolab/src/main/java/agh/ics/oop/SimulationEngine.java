package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private List<MoveDirection> directions;
    private final IWorldMap map;
    private final List<Animal> animals;
    private final List<IMapUpdateObserver> observers;
    private int moveDelay = 0;

    public Vector2d getAnimalPositions(int i) {
        return animals.get(i).getPosition();
    }

    public SimulationEngine(IWorldMap map, List<Vector2d> positions) {
        this.map = map;
        this.animals = new ArrayList<>();
        this.observers = new ArrayList<>();

        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            if(this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    public SimulationEngine(List<MoveDirection> directions, IWorldMap map, List<Vector2d> positions) {
        this.directions = directions;
        this.map = map;
        this.animals = new ArrayList<>();
        this.observers = new ArrayList<>();

        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            if(this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this.map);

        int animalsAmm = this.animals.size();
        for (int i = 0; i < directions.size(); i++) {
            this.animals.get(i % animalsAmm).move(this.directions.get(i));
            System.out.println(this.map);

            for (IMapUpdateObserver observer : this.observers) {
                observer.positionChanged();
            }

            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException ex) {
                System.out.println("Simulation has been stopped -> " + ex);
            }
        }
    }

    public void addObserver(IMapUpdateObserver observer) {
        this.observers.add(observer);
    }

    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }
}
