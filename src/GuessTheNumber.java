import java.util.Random;
import java.util.Scanner;

interface NumberSource {
    int generateNumber(int min, int max);
}

class RandomNumberGenerator implements NumberSource {
    private Random random = new Random();

    @Override
    public int generateNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}

public class GuessTheNumber {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Виберіть рівень складності:");
            System.out.println("1. Легкий (1-50)");
            System.out.println("2. Середній (1-100)");
            System.out.println("3. Важкий (1-200)");

            int min = 1, max = 100;
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    max = 50;
                    break;
                case 2:
                    max = 100;
                    break;
                case 3:
                    max = 200;
                    break;
                default:
                    System.out.println("Невірний вибір. Вибрано середній рівень (1-100).");
                    max = 100;
            }

            NumberSource numberSource = new RandomNumberGenerator();
            int numberToGuess = numberSource.generateNumber(min, max);
            playGame(numberToGuess, min, max);

            System.out.println("Бажаєте зіграти ще раз? (y/n)");
            char playAgain = scanner.next().charAt(0);
            if (playAgain != 'y' && playAgain != 'Y') {
                break;
            }
        }
        scanner.close();
    }

    private static void playGame(int numberToGuess, int min, int max) {
        int numberOfTries = 0;
        boolean hasGuessed = false;

        System.out.println("Я загадал число від " + min + " до " + max + ". Спробуйте його відгадати!");

        while (!hasGuessed) {
            System.out.print("Введіть своє число: ");
            int guess = scanner.nextInt();
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
