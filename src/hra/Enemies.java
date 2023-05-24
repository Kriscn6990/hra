package hra;

public class Enemies extends Items{
    private int hp;
    private int attack;
    private String difficulty;

    public Enemies() {
        this.hp = 0;
        this.attack = 0;
        this.difficulty ="";
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
