package nature.predators;

import island.Cell;
import nature.Animal;
import nature.herbivore.Caterpillar;
import nature.herbivore.Duck;
import nature.herbivore.Mouse;
import nature.herbivore.Rabbit;

import java.util.ArrayList;

public class Fox extends Predator {
    public Fox() {
        super(2, 2, 8, 30);
    }

    @Override
    public void eat(Cell cell) {
        boolean ate = false;
        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
            if (!ate) {
                if (animal instanceof Mouse && tryEatAnimal(animal, 0.9)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Лиса в [%d,%d] съела мышь %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Rabbit && tryEatAnimal(animal, 0.7)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Лиса в [%d,%d] съела кролика %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Duck && tryEatAnimal(animal, 0.6)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Лиса в [%d,%d] съела утку %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Caterpillar && tryEatAnimal(animal, 0.4)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Лиса в [%d,%d] съела гусеницу %n", cell.getX(), cell.getY());
                    ate = true;
                }
            }
        }
        decreaseSatiety();
    }
}
