package cat.itacademy.console;

import java.util.Scanner;

public class ConsoleUtils {

    public static void pressEnterToContinue(Scanner scanner) {
        System.out.println("\n⏎ Presiona ENTER para continuar...");
        scanner.nextLine();
    }
}
