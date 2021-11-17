package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private List<MoveDirection> directions;
    private IWorldMap map;
    private List<Animal> animals;

    public List<Animal> getAnimals() {
        return animals;
    }

    public SimulationEngine(List<MoveDirection> directions, IWorldMap map, List<Vector2d> positions) {
        this.directions = directions;
        this.map = map;
        this.animals = new ArrayList<>();

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
        }
    }
}
