import island.Island;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите ширину и длину: ");
        Scanner scanner = new Scanner(System.in);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        Island island = new Island(width, height);
        island.initialize();
        island.startSimulation(100  );
        while (!island.isSimulationFinished()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Итоговая статистика:");
        island.printStatistics();
        scanner.close();
    }
}

