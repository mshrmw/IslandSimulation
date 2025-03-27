package nature.herbivore;

import island.Cell;
import nature.Animal;

import java.util.ArrayList;

public class Boar extends Herbivore {
    public Boar() {
        super(50, 2, 400, 50);
    }
    @Override
    public void eat(Cell cell) {
        boolean ate = false;
        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
            if ((animal instanceof Mouse && random.nextDouble() < 0.5) ||
                    (animal instanceof Caterpillar && random.nextDouble() < 0.9)) {
                cell.getAnimals().remove(animal);
                satiety = Math.min(maxSatiety, satiety + animal.weight * 0.3);
                System.out.printf("Кабан съел %s в [%d,%d] %n",
                        animal.getClass().getSimpleName(),
                        cell.getX(), cell.getY(),
                        animal.weight * 0.3);
                ate = true;
                break;
            }
        }
        if (!ate && cell.getPlant() != null) {
            cell.removePlant();
            satiety = Math.min(maxSatiety, satiety + 8);
            System.out.printf("Кабан поел растение в [%d,%d]%n",
                    cell.getX(), cell.getY());
        }
        decreaseSatiety();
    }
}
