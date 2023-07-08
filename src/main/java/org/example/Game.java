package org.example;
import java.util.List;
public interface Game {
    void start(int lenghtWord, int countTry);
    Answer inputValue(String input);
    GameStatus getGameStatus();

    List<String> getLogo();
    void restart();
}
