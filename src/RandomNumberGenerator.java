import java.util.Random;

class RandomNumberGenerator implements NumberSource {
    private final Random random = new Random();

    @Override
    public int generateNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}