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
        return input.nextInt();
    }
    public void fightExecute(Player player,Enemies enemies){
        int playerHealth = player.getHp();
        int enemyHP = enemies.getHp();
        int enemyAttack = enemies.getAttack();
        player.setHp(playerHealth-enemyAttack);
    }


}
