package hra;

import java.util.Scanner;

public class Fight {

    public int fightMenu(Player player,Enemies enemy){

        System.out.println(enemy.getDifficulty());
        Scanner input=new Scanner(System.in);
        System.out.println("Volby:");
        System.out.println("1. Utok");
        System.out.println("2. Silnejsi utok (mensi sance ze se provede)");
        fightExecute(player,enemy);
        input.reset();
        System.out.println("toto cte scanner z konzole: " + input.nextLine());
        return 1;
    }
    public void fightExecute(Player player,Enemies enemies){
        int playerHealth = player.getHp();
        int enemyHP = enemies.getHp();
        int enemyAttack = enemies.getAttack();
        switch (enemies.getDifficulty()) {
            case "Lehky":{player.setScore(player.getScore()+50);}break;
            case "Stredni":{player.setScore(player.getScore()+100);}break;
            case "Tezky":{player.setScore(player.getScore()+150);}
        }
        player.setHp(playerHealth-enemyAttack);
    }


}
