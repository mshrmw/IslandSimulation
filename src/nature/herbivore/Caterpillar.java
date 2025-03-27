package nature.herbivore;

import island.Cell;
import island.Island;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(0, 0, 0.01, 1000);
    }
    @Override
    public void move(Cell currentCell, Island island) {
        System.out.println("Гусеница остаётся на месте в клетке [" +
                currentCell.getX() + "," + currentCell.getY() + "]");
    }
}