package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public abstract class AbstractGame implements Game{
    protected int wordLenght;
    protected int tryCount;
    protected String word;
    protected GameStatus status = GameStatus.INIT;
    protected List<String> logo = new ArrayList<>();
    protected int humanTry;


    public AbstractGame(int wordLenght, int tryCount, String word, GameStatus status) {
        this.wordLenght = wordLenght;
        this.tryCount = tryCount;
        this.word = word;
        this.status = status;
        this.logo = new ArrayList<>();

        this.humanTry = 0;
    }

    public AbstractGame(){

    }

    @Override
    public void start(int lenghtWord, int countTry) {
        status = GameStatus.START;
        this.wordLenght = lenghtWord;
        this.tryCount = countTry;
        this.word = generateWord();
        this.logo.add(String.format("Запуск игры %s; Число попыток: %d; Загаданное слово: %s",
                getNameGame(), countTry, this.word));
        System.out.println("word = " + word);
    }


    private String generateWord() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        List<String> strList = generateCharList();
        for (int i = 0; i < wordLenght; i++) {
            int ind = r.nextInt(strList.size());
            sb.append(strList.get(ind));
            strList.remove(ind);
        }
        return sb.toString();
    }

    public abstract List<String> generateCharList();
    public abstract String getNameGame();

    @Override
    public Answer inputValue(String input) {
        humanTry++;
        if (--tryCount == 0){
            int bools = 0;
            int cows = 0;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == word.charAt(i)){
                    bools++;
                }
                if (word.contains(String.valueOf(input.charAt(i)))) {
                    cows++;
                }
            }
            logo.add(String.format("Попытка № %d; введенный выриант - %s; " +
                    "быки = %d, коровы = %d", humanTry, input, bools, cows));
            if (bools == wordLenght) {
                logo.add(String.format("Победил с %d попытки; быки = %d, коровы = %d", humanTry, bools, cows));
                status = GameStatus.WIN;
            } else  {
                logo.add("Проигрыш");
                status = GameStatus.FINISH;
            }
            return new Answer(bools, cows);
        }
        int bools = 0;
        int cows = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == word.charAt(i)){
                bools++;
            }
            if (word.contains(String.valueOf(input.charAt(i)))) {
                cows++;
            }
        }
        logo.add(String.format("Попытка № %d; введенный выриант - %s; " +
                "быки = %d, коровы = %d", humanTry, input, bools, cows));
        if (bools == wordLenght) {
            logo.add(String.format("Победил с %d попытки; быки = %d, коровы = %d", humanTry, bools, cows));
            status = GameStatus.WIN;
        }
        return new Answer(bools, cows);
    }

    @Override
    public GameStatus getGameStatus() {
        return status;
    }

    @Override
    public List<String> getLogo(){
        return logo;
    }
    @Override
    public void restart(){
        this.logo = new ArrayList<>();
        this.humanTry = 0;
    }
}
