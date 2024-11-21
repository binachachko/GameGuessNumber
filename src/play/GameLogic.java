package play;
import player.Player;

public interface GameLogic {
    void playGame(int numberToGuess, int min, int max, Player player);  // Видаляємо статичний метод
}
