import play.GameLogic;
import play.PlayGameEasy;
import play.PlayGameHard;
import player.AIAssistedPlayer;
import player.ManualPlayer;
import player.Player;
import source.KeyboardNumberSource;
import source.NumberSource;
import source.RandomNumberSource;

import java.util.Scanner;

public class GuessTheNumber {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Виберіть рівень складності:");
            System.out.println("1. Легкий (1-50)");
            System.out.println("2. Середній (1-100)");
            System.out.println("3. Важкий (1-200)");

            int min = 1;
            int choice = scanner.nextInt();

            int max = switch (choice) {
                case 1 -> 50;
                case 2 -> 100;
                case 3 -> 200;
                default -> {
                    System.out.println("Невірний вибір. Вибрано середній рівень (1-100).");
                    yield 100;
                }
            };

            NumberSource numberSource = selectNumberSource();
            int numberToGuess = numberSource.generateNumber(min, max);

            Player player = selectPlayer();

            GameLogic gameLogic = selectGameLogic();
            gameLogic.playGame(numberToGuess, min, max, player);

            System.out.println("Бажаєте зіграти ще раз? (y/n)");
            char playAgain = scanner.next().charAt(0);
            if (playAgain != 'y' && playAgain != 'Y') {
                break;
            }
        }
        scanner.close();
    }

    private static NumberSource selectNumberSource() {
        System.out.println("Виберіть джерело для загадування числа:");
        System.out.println("1. Випадкове число");
        System.out.println("2. Введення числа вручну");

        int sourceChoice = scanner.nextInt();
        return switch (sourceChoice) {
            case 1 -> new RandomNumberSource();
            case 2 -> new KeyboardNumberSource();
            default -> {
                System.out.println("Невірний вибір. Вибрано випадкове число.");
                yield new RandomNumberSource();
            }
        };
    }

    private static Player selectPlayer() {
        System.out.println("Виберіть тип гравця:");
        System.out.println("1. Ручний гравець");
        System.out.println("2. AI гравець");

        int playerChoice = scanner.nextInt();
        return switch (playerChoice) {
            case 1 -> new ManualPlayer();
            case 2 -> new AIAssistedPlayer();
            default -> {
                System.out.println("Невірний вибір. Вибрано ручного гравця.");
                yield new ManualPlayer();
            }
        };
    }

    private static GameLogic selectGameLogic() {
        System.out.println("Виберіть тип підказок:");
        System.out.println("1. Легкий режим (тільки правильні підказки)");
        System.out.println("2. Важкий режим (неправильні підказки також)");

        int gameChoice = scanner.nextInt();
        return switch (gameChoice) {
            case 1 -> new PlayGameEasy();
            case 2 -> new PlayGameHard();
            default -> {
                System.out.println("Невірний вибір. Вибрано легкий режим.");
                yield new PlayGameEasy();
            }
        };
    }
}
