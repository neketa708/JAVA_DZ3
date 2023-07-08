package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        game();


    }

    /**
     * @apiNote Дописать игру быки-коровы:
     * - реализовать английскую версию игры
     * - сделать логирование действий и по запросу пользователя посмотреть всю историю игры
     * - реализовать перезапуск игры
     */
    public static void game() {
        AbstractGame game1 = new NumberGame();
        AbstractGame game2 = new RuGame();
        AbstractGame game3 = new EngGame();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        String[] tmp = {"1. Угадай число.", "2. Угадай слово на русском.",
                "3. Угадай слово на английском.", "4. Посмотреть логи.", "q. Выход."};
        while (flag) {
            System.out.println("Выберите игру: ");
            for (String s : tmp) {
                System.out.println(s);
            }
            String str = scanner.nextLine();

            switch (str.charAt(0)) {
                case ('1') -> gameStart(game1, scanner);
                case ('2') -> gameStart(game2, scanner);
                case ('3') -> gameStart(game3, scanner);
                case ('4') -> {
                    List<List<String>> listHistory = Arrays.asList(game1.getLogo(), game2.getLogo(), game3.getLogo());
                    for (List<String> stringList : listHistory) {
                        System.out.println(stringList);
                    }
                }
                default -> flag = false;
            }
        }
        System.out.println("Классно поиграли, до новых встреч!");
    }

    /**
     * Впомогательный метод для запуска одной игры
     * @param game1 игра
     * @param scanner сканер
     */
    private static void gameStart(AbstractGame game1, Scanner scanner) {

        boolean flag = true;
        while (flag) {
            System.out.println("Введите длинну слова");
            int wordLengh = scanner.nextInt();
            System.out.println("Введите количество попыток");
            int tryCount = scanner.nextInt();
            scanner.nextLine();
            game1.start(wordLengh, tryCount);
            int tmp = 0;
            boolean isFinish = false;
            boolean isWinner = false;
            while (!(isFinish || isWinner)) {
                System.out.printf("* Попытка № %d. Что загадал компьютер?%n", ++tmp);
                String str = scanner.nextLine();

                Answer answer = game1.inputValue(str);
                if (answer != null) {
                    System.out.println("answer = " + answer);
                }
                isFinish = game1.getGameStatus() == GameStatus.FINISH;
                isWinner = game1.getGameStatus() == GameStatus.WIN;
            }
            if (isWinner) {
                System.out.println("Вы победили");
                System.out.println("Съиграем еще разок?!(y/n)");
                String str1 = scanner.nextLine();
                if (str1.charAt(0) == 'y') {
                    game1.restart();
                } else {
                    flag = false;
                }
            } else {
                System.out.println("Вы проиграли");
                System.out.println("Съиграем еще разок?!(y/n)");
                String str2 = scanner.nextLine();
                if (str2.charAt(0) == 'y') {
                    game1.restart();
                } else {
                    flag = false;
                }
            }
        }
    }
}