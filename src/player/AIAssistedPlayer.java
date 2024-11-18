import java.util.Random;

class AIAssistedPlayer implements Player {
    private final Random random = new Random();

    @Override
    public int makeGuess(int min, int max) {
        int guess = random.nextInt(max - min + 1) + min;
        System.out.println("AI гравець припускає: " + guess);
        return guess;
    }
}
