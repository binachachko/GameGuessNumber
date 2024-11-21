package play;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayGameHard implements GameLogic{

    @Override
    public void playGame(int numberToGuess, int min, int max, Player player) {
        int numberOfTries = 0;
        boolean hasGuessed = false;
        final Random random = new Random();

        int wrongHints = random.nextInt(3, 7);
        int wrongHintsRemaining = wrongHints;

        List<String> hints = new ArrayList<>();

        for (int i = 0; i < (max - min); i++) {
            hints.add("correct");
        }

        System.out.println("Я загадав число від " + min + " до " + max + ". Спробуйте його відгадати! А я спробую вам заважати!");

        while (!hasGuessed) {
            System.out.print("Введіть своє число: ");
            int guess = player.makeGuess(min, max);
            numberOfTries++;

            if (guess < min || guess > max) {
                System.out.println("Число має бути в межах від " + min + " до " + max + ". Спробуйте ще раз.");
            } else if (guess < numberToGuess) {
                System.out.println("Загадане число більше.");

                if (wrongHintsRemaining > 0 && random.nextBoolean()) {
                    System.out.println("(Неправильна підказка!) Загадане число менше.");
                    wrongHintsRemaining--;
                }
            } else if (guess > numberToGuess) {
                System.out.println("Загадане число менше.");

                if (wrongHintsRemaining > 0 && random.nextBoolean()) {
                    System.out.println("(Неправильна підказка!) Загадане число більше.");
                    wrongHintsRemaining--;
                }
            } else {
                System.out.println("Ви вгадали число! Це було " + numberToGuess);
                System.out.println("Кількість спроб: " + numberOfTries);
                System.out.println("Кількість неправильних підказок: " + wrongHints);
                hasGuessed = true;
            }
        }
        }
}
