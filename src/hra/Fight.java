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
    public static void showcombatInfo(Enemies enemy,Player player){
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        String blueColor = "\033[0;34m";
        System.out.print("Nepritel");
        System.out.println("\t vs \t" + player.getName());
        System.out.print("HP   ["+redColor);
        for(int i=0;i<enemy.getHp();i++){
            System.out.print("\u2665");
        }
        System.out.print(resetColor+"]");
        int lengthforhp,lengthforattack;

        if(enemy.getAttack() < enemy.getHp()){
            lengthforattack = enemy.getHp()+1;
            lengthforhp = enemy.getAttack();
        }
        else {
            lengthforattack = enemy.getHp();
            lengthforhp = enemy.getAttack()+1;
        }

        for(int i=0;i<lengthforhp ;i++){
            System.out.print(" ");
        }


        System.out.print("HP["+redColor);
        for(int i=0;i<player.getHp();i++){
            System.out.print("\u2665");
        }
        System.out.println(resetColor+"]");

        System.out.print("Utok ["+blueColor);
        for (int i=0;i<enemy.getAttack();i++){
            System.out.print("\u2694");
        }
        System.out.print(resetColor+"]");

        for(int i=0;i< lengthforattack;i++){
            System.out.print(" ");
        }

        System.out.print("Utok["+blueColor);
        for (int i=0;i<player.getAttack();i++){
            System.out.print("\u2694");
        }
        System.out.println(resetColor+"]");
    }


    public char fightMenu(Player player, Enemies enemy){
        char x;
        System.out.println(enemy.getDifficulty());
        Scanner input=new Scanner(System.in);
        System.out.println("Volby:");
        System.out.println("1. Utok");
        System.out.println("2. Utect");
        if(player.isSword()) System.out.println("3. Pouzit mec");
        x = listenforkey();
        if(x == '1' || x == '3')
        fightExecute(player,enemy,x);
        System.out.println();
        return x;
    }
    public void fightExecute(Player player, Enemies enemies, char option){
        int playerHealth = player.getHp();
        int enemyHP = enemies.getHp();
        int enemyAttack = enemies.getAttack();
        int playerAttack = player.getAttack();
        switch (option) {
            case '1':{
                System.out.println("vas hp po souboji " + (playerHealth-enemyAttack));
                player.setHp(playerHealth-enemyAttack);
                enemies.setHp(enemies.getHp()-playerAttack);
            }
            break;
            case '3':{
                player.setSword(false);
                enemies.setHp(0) ;
            }
        }

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
