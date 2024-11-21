package player;

import java.util.Scanner;

public class ManualPlayer implements Player {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int makeGuess(int min, int max) {
        System.out.print("Введіть своє припущення (від " + min + " до " + max + "): ");
        return scanner.nextInt();
    }
}
