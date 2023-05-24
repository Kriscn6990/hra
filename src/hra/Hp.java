package hra;

public class Hp extends Items{
    private int countOfHealthToAdd;

    public Hp() {
        this.countOfHealthToAdd = 0;
    }

    public int getCountOfHealthToAdd() {
        return countOfHealthToAdd;
    }

    public void setCountOfHealthToAdd(int countOfHealthToAdd) {
        this.countOfHealthToAdd = countOfHealthToAdd;
    }
}
