package org.example;

public class Answer {
    private int bulls;
    private int cows;

    public Answer() {
    }

    @Override
    public String toString() {
        return "быки=" + bulls + "," + "коровы=" + cows;
    }

    public Answer(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public int getBulls() {
        return bulls;
    }



    public int getCows() {
        return cows;
    }
}
