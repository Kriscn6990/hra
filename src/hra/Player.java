package hra;

public class Player {
    private int score=0;
    private String name;
    private int hp;

    public Player(String name, int hp,int score) {
        this.name = name;
        this.hp = hp;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}