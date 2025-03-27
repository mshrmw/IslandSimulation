package nature.herbivore;

import island.Cell;
import nature.Animal;

import java.util.ArrayList;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.01, 1, 0.05, 500);
    }
    @Override
    public void eat(Cell cell) {
        boolean ate = false;
        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
            if (animal instanceof Caterpillar) {
                cell.getAnimals().remove(animal);
                satiety = Math.min(maxSatiety, satiety + 0.02);
                ate = true;
                System.out.printf("Мышь в [%d,%d] съела гусеницу %n", cell.getX(), cell.getY());
                break;
            }
        }
        if (!ate && cell.getPlant() != null) {
            cell.removePlant();
            satiety = Math.min(maxSatiety, satiety + 0.01);
            System.out.printf("Мышь в [%d,%d] поела растение %n", cell.getX(), cell.getY());
        }
        decreaseSatiety();
    }
}
