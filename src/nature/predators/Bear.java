package nature.predators;

import island.Cell;
import nature.Animal;
import nature.herbivore.*;

import java.util.ArrayList;

public class Bear extends Predator {
    public Bear() {
        super(80, 2, 500, 5);
    }
    @Override
    public void eat(Cell cell) {
        boolean ate = false;
        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
            if (!ate) {
                if (animal instanceof Mouse && tryEatAnimal(animal, 0.9)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел мышь %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Rabbit && tryEatAnimal(animal, 0.8)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел кролика %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Deer && tryEatAnimal(animal, 0.8)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел оленя %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Boa && tryEatAnimal(animal, 0.8)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел удава %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Goat && tryEatAnimal(animal, 0.7)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел козу %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Sheep && tryEatAnimal(animal, 0.7)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел овцу %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Boar && tryEatAnimal(animal, 0.5)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел кабана %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Horse && tryEatAnimal(animal, 0.4)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел лошадь %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Buffalo && tryEatAnimal(animal, 0.2)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел буйвола %n", cell.getX(), cell.getY());
                    ate = true;
                } else if (animal instanceof Duck && tryEatAnimal(animal, 0.1)) {
                    cell.getAnimals().remove(animal);
                    System.out.printf("Медведь в [%d,%d] съел утку %n", cell.getX(), cell.getY());
                    ate = true;
                }
            }
        }
        decreaseSatiety();
    }
}
