package hra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Welcome to this superhero game\n please write your superhero name:");
        //tvorba hrace a mapy
        Player player = new Player(input.nextLine(),5,0);
        Map firstmap = new Map(10,7,25);
        //
        int [] position=new int[2];
        position[0]=1;
        position[1]=1;
        char x;
        firstmap.createMap();
        //mapa
        do{
            showplayerinfo(player.getHp(),player.getName(),player.getScore());
            firstmap.showMap();
            System.out.println("Move: WASD/wasd");
            System.out.println("End: 0");
            x=input.next().charAt(0);
            walk(firstmap.getArrayMap(),position,x);
            for(int i=0;i<20;i++){
                System.out.println();
            }
        }while(x!='0');
    }
    public static void walk(char map[][], int position[], char x) {
        int row = position[0];
        int column = position[1];
        if ((x == 'W' || x == 'w')&&map[row-1][column]=='.') {
            map[row - 1][column] = '#';
            map[row][column] = '.';
            position[0]--;
        } else if ((x == 'S' || x == 's')&&map[row+1][column]=='.') {
            map[row + 1][column] = '#';
            map[row][column] = '.';
            position[0]++;
        } else if ((x == 'A' || x == 'a')&&map[row][column-1]=='.') {
            map[row][column - 1] = '#';
            map[row][column] = '.';
            position[1]--;
        } else if ((x == 'D' || x == 'd')&&map[row][column+1]=='.') {
            map[row][column + 1] = '#';
            map[row][column] = '.';
            position[1]++;
        }
    }
    //delete later
    public static boolean checkWall(char map[][], int row, int column) {
        if (map[row][column] == '│' || map[row][column] == '─' || map[row][column] == '┐' || map[row][column] == '┌' || map[row][column] == '└' || map[row][column] == '┘') {
            return false;
        }
        else{
            return true;
        }
    }
    //show hp
    public static void showplayerinfo(int hp,String name,int score){
        System.out.println("Playerinfo");
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
            if(i==59) System.out.println();
        }

        System.out.print("HP ");
        System.out.print("[");
        for (int i = 0; i < hp; i++) {
            System.out.print("\u2665");

        }
        System.out.print("]");
        System.out.print("\tSuperHero -> " + name + "\t");
        System.out.println("\tPlayerscore -> " + score + "\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
            if(i==59) System.out.println();
        }

    }
}
