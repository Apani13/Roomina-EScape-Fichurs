package cat.itacademy.presentation;

import java.util.Scanner;

public class ConsoleUtils {

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }

    public static void pressEnterToContinue(Scanner scanner) {
        System.out.println("\n⏎ Presiona ENTER para continuar...");
        scanner.nextLine();
    }
}
