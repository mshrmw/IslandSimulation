package nature.herbivore;

import island.Cell;
import nature.Animal;

import java.util.ArrayList;

public class Duck extends Animal {
    public Duck() {
        super(0.15, 4, 1, 200);
    }
    @Override
    public void eat(Cell cell) {
        boolean ate = false;

        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
            if (animal instanceof Caterpillar) {
                cell.getAnimals().remove(animal);
                satiety = Math.min(maxSatiety, satiety + 0.1);
                ate = true;
                System.out.println("Утка съела гусеницу");
                break;
            }
        }
        if (!ate && cell.getPlant() != null) {
            cell.removePlant();
            satiety = Math.min(maxSatiety, satiety + 0.05);
            System.out.println("Утка поела растение");
        }
        decreaseSatiety();
    }
}
