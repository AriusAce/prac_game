package com.example.trung.memogame;

public class Highscore {
    private String name;
    private String score;
    private String round;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String  getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Highscore(String name, String score, String round) {

        this.name = name;
        this.score = score;
        this.round = round;
    }
}
