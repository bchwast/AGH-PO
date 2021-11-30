package agh.ics.oop;

import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    private final int grassAmm;
    private final int maxCord;

    public GrassField(int grassAmm) {
        this.grassAmm = grassAmm;
        this.maxCord = (int) Math.ceil(Math.sqrt(grassAmm * 10));
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
                this.mapElementsList.put(grassPosition, new Grass(grassPosition));
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = objectAt(position);
        if (object instanceof Grass) {
            mapElementsList.remove(position);
            generateGrass(1);
        }
        return super.canMoveTo(position);
    }

    @Override
    public String toString() {
        for (Map.Entry<Vector2d, AbstractWorldMapElement> element : mapElementsList.entrySet()) {
            if (element.getValue() instanceof Animal)
                this.lowerLeftCorner = this.lowerLeftCorner.lowerLeft(element.getKey());
                this.upperRightCorner = this.upperRightCorner.upperRight(element.getKey());
        }
        return super.toString();
    }
}
