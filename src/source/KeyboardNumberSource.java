package source;

import java.util.Scanner;

public class KeyboardNumberSource implements NumberSource {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int generateNumber(int min, int max) {
        int number;
        do {
            System.out.print("Введіть число, яке має бути загадане (в межах " + min + "-" + max + "): ");
            number = scanner.nextInt();
            if (number < min || number > max) {
                System.out.println("Число поза межами. Спробуйте ще раз.");
            }
        } while (number < min || number > max);
        return number;
    }
}
