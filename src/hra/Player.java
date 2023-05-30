package hra;

public class Player {
    private static int score=0;
    private String name;
    private int hp;
    private int attack;

    private boolean sword = false;

    public boolean isSword() {
        return sword;
    }

    public void setSword(boolean sword) {
        this.sword = sword;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Player(String name, int hp,int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
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
