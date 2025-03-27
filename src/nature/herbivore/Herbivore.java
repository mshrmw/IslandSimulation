package nature.herbivore;

import island.Cell;
import nature.Animal;
import nature.Plant;

public abstract class Herbivore extends Animal {
    public Herbivore(double maxSatiety, int speed, double weight, int maxPerCell) {
        super(maxSatiety, speed, weight, maxPerCell);
    }
    @Override
    public void eat(Cell cell) {
        Plant plant = cell.getPlant();
        if (plant != null) {
            cell.removePlant();
            satiety = Math.min(maxSatiety, satiety + plant.getWeight() * 0.1);
            System.out.printf("%s поел растение в клетке [%d,%d]%n",
                    this.getClass().getSimpleName(),
                    cell.getX(), cell.getY()
            );
        }
        else {
            System.out.printf("%s в клетке [%d,%d] не нашёл растений!%n",
                    this.getClass().getSimpleName(),
                    cell.getX(), cell.getY());
        }
        decreaseSatiety();
    }
}
