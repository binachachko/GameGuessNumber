package play;
import player.Player;

public class PlayGameEasy implements GameLogic {

    @Override
    public void playGame(int numberToGuess, int min, int max, Player player) {
        int numberOfTries = 0;
        boolean hasGuessed = false;

        System.out.println("Я загадав число від " + min + " до " + max + ". Спробуйте його відгадати!");

        while (!hasGuessed) {
            System.out.print("Введіть своє число: ");
            int guess = player.makeGuess(min, max);
            numberOfTries++;

            if (guess < min || guess > max) {
                System.out.println("Число має бути в межах від " + min + " до " + max + ". Спробуйте ще раз.");
            } else if (guess < numberToGuess) {
                System.out.println("Загадане число більше.");
            } else if (guess > numberToGuess) {
                System.out.println("Загадане число менше.");
            } else {
                System.out.println("Ви вгадали число! Це було " + numberToGuess);
                System.out.println("Кількість спроб: " + numberOfTries);
                hasGuessed = true;
            }
        }
    }
}
