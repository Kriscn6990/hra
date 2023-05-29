package hra;

import com.github.kwhat.jnativehook.GlobalScreen;

import java.util.Scanner;

public class Fight {
    public int begin(int hp,int attack,String name,Player player,Enemies enemy){
        char t;
        while (enemy.getHp() > 0 && player.getHp() > 0) {
            showcombatInfo(enemy,player);
            t = fightMenu(player, enemy);
            if(t == '2')return 2;
        }
        if(enemy.getHp() <= 0) {
            switch (enemy.getDifficulty()) {
                case "Lehky": {
                    player.setScore(player.getScore() + 50);
                }
                break;
                case "Stredni": {
                    player.setScore(player.getScore() + 100);
                }
                break;
                case "Tezky": {
                    player.setScore(player.getScore() + 150);
                }
            }
        }
        if (player.getHp() > 0) return 1;
        else return 0;
    }
    public void showcombatInfo(Enemies enemy,Player player){
        System.out.print("Nepritel\t");
        System.out.print("HP   [");
        for(int i=0;i<enemy.getHp();i++){
            System.out.print("\u2665");
        }
        System.out.print("] ");
        System.out.print("Utok [");
        for (int i=0;i<enemy.getAttack();i++){
            System.out.print("\u2694");
        }
        System.out.print("]");
        System.out.print("\t vs \t" + player.getName()+"\t");
        System.out.print("HP[");
        for(int i=0;i<player.getHp();i++){
            System.out.print("\u2665");
        }
        System.out.print("] ");
        System.out.print("Utok[");
        for (int i=0;i<player.getAttack();i++){
            System.out.print("\u2694");
        }
        System.out.println("]");
    }

    public char fightMenu(Player player, Enemies enemy){
        char x;
        System.out.println(enemy.getDifficulty());
        Scanner input=new Scanner(System.in);
        System.out.println("Volby:");
        System.out.println("1. Utok");
        System.out.println("2. Utect");
        x = listenforkey();
        if(x == '1')
        fightExecute(player,enemy);
        System.out.println();
        return x;
    }
    public void fightExecute(Player player, Enemies enemies){
        int playerHealth = player.getHp();
        int enemyHP = enemies.getHp();
        int enemyAttack = enemies.getAttack();
        int playerAttack = player.getAttack();
        player.setHp(playerHealth-enemyAttack);
        enemies.setHp(enemies.getHp()-playerAttack);
    }
    private char listenforkey(){
        GlobalKeyListener listener = new GlobalKeyListener();
        try {
            GlobalScreen.addNativeKeyListener(listener);
            // Wait for a single key press
            synchronized (listener) {
                listener.wait();
            }
            GlobalScreen.removeNativeKeyListener(listener);
        } catch ( InterruptedException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        return listener.getPressedKey().charAt(0);
    }

}
