package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    private final int grassAmm;
    private final int maxCord;
    private final MapBoundary mapBoundary;

    public GrassField(int grassAmm) {
        this.grassAmm = grassAmm;
        this.maxCord = (int) Math.ceil(Math.sqrt(grassAmm * 10));
        this.mapBoundary = new MapBoundary();
        generateGrass(grassAmm);
    }

    public void generateGrass(int amm) {
        Random rand = new Random();
        int cnt = 0;

        while (cnt < amm) {
            int x = rand.nextInt(this.maxCord);
            int y = rand.nextInt(this.maxCord);

            Vector2d grassPosition = new Vector2d(x, y);
            if (! isOccupied(grassPosition)) {
                cnt++;
                Grass grass = new Grass(grassPosition);
                this.mapBoundary.addElement(grass);
                this.mapElementsList.put(grassPosition, grass);
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object element = objectAt(position);
        if (element instanceof Grass) {
            this.mapBoundary.removeElement((Grass) element);
            this.mapElementsList.remove(position);
            generateGrass(1);
        }
        return super.canMoveTo(position);
    }

    @Override
    public boolean place(Animal animal) {
        super.place(animal);
        this.mapBoundary.addElement(animal);
        return true;
    }

    @Override
    public String toString() {
        this.lowerLeftCorner = this.mapBoundary.getLowerLeft();
        this.upperRightCorner = this.mapBoundary.getUpperRight();
        return super.toString();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = this.mapElementsList.get(oldPosition);
        this.mapElementsList.put(newPosition, element);
        this.mapBoundary.positionChanged(oldPosition, newPosition);
        this.mapElementsList.remove(oldPosition);
    }
}
