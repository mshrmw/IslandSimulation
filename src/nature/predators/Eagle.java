package nature.predators;

import island.Cell;
import nature.Animal;
import nature.herbivore.Duck;
import nature.herbivore.Mouse;
import nature.herbivore.Rabbit;

import java.util.ArrayList;

public class Eagle extends Predator {
    public Eagle() {
        super(1, 3, 6, 20);
    }

    @Override
    public void eat(Cell cell) {
        boolean ate = false;

        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
            if (!ate) {
                if (animal instanceof Rabbit && tryEatAnimal(animal, 0.9)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Орел в [%d,%d] съел кролика %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Mouse && tryEatAnimal(animal, 0.9)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Орел в [%d,%d] съел мышь %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Duck && tryEatAnimal(animal, 0.8)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Орел в [%d,%d] съел утку %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Fox && tryEatAnimal(animal, 0.1)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Орел в [%d,%d] съел лису %n", cell.getX(), cell.getY());
                    ate = true;
                }
            }
        }
        decreaseSatiety();
    }
}