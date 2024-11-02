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

            NumberSource numberSource = (NumberSource) selectNumberSource();
            int numberToGuess = numberSource.generateNumber(min, max);

            Player player = selectPlayer();
            playGame(numberToGuess, min, max, player);


            System.out.println("Бажаєте зіграти ще раз? (y/n)");
            char playAgain = scanner.next().charAt(0);
            if (playAgain != 'y' && playAgain != 'Y') {
                break;
            }
        }
        scanner.close();
    }

    private static Object selectNumberSource() {
        System.out.println("Виберіть джерело для загадування числа:");
        System.out.println("1. Випадкове число");
        System.out.println("2. Введення числа вручну");

        int sourceChoice = scanner.nextInt();
        return switch (sourceChoice) {
            case 1 -> new RandomNumberGenerator();
            case 2 -> new KeyboardNumberInput();
            default -> {
                System.out.println("Невірний вибір. Вибрано випадкове число.");
                yield new RandomNumberGenerator();
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


    private static void playGame(int numberToGuess, int min, int max, Player player) {
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
