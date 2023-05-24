package hra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Welcome to this superhero game\n please choose your superhero\n batman or spiderman:");
        //tvorba hrace a mapy
        Player player = new Player(input.nextLine(),5,0);
        Map firstmap = new Map(20,7,25);

        String asciiImagespider = readASCIIImage("ascii_art_spider.txt");
        String asciiImagebatman = readASCIIImage("ascii_art.txt");
        System.out.println(asciiImagespider);


        int [] position=new int[2];
        position[0]=1;
        position[1]=1;
        char x;
        firstmap.createMap();
        //mapa
        do{
            showplayerinfo(player.getHp(),player.getName(),player.getScore());
            firstmap.showMap();
            for(int i=0;i<firstmap.getHp().length;i++){
                if(onItem(position,firstmap.getHp()[i].getItemPosition())){
                    System.out.println("Byl ziskan item");
                }
            }
            for(int i=0;i<firstmap.getEnemies().length;i++){
                if (onItem(position,firstmap.getEnemies()[i].getItemPosition())) {
                    Fight fight=new Fight();
                    if(fight.fightMenu()==1){
                        
                    }
                }
            }
            System.out.println("Pohyb: WASD/wasd");
            System.out.println("Konec: 0");
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
        if ((x == 'W' || x == 'w')&&(map[row-1][column]=='.'||map[row-1][column]=='?')) {
            map[row - 1][column] = '#';
            map[row][column] = '.';
            position[0]--;
        } else if ((x == 'S' || x == 's')&&(map[row+1][column]=='.'||map[row+1][column]=='?')) {
            map[row + 1][column] = '#';
            map[row][column] = '.';
            position[0]++;
        } else if ((x == 'A' || x == 'a')&&(map[row][column-1]=='.'||map[row][column-1]=='?')) {
            map[row][column - 1] = '#';
            map[row][column] = '.';
            position[1]--;
        } else if ((x == 'D' || x == 'd')&&(map[row][column+1]=='.'||map[row][column+1]=='?')) {
            map[row][column + 1] = '#';
            map[row][column] = '.';
            position[1]++;
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

    public static boolean onItem(int [] position, int [] itemPosition){
        if(position[0]==itemPosition[0]&&position[1]==itemPosition[1]){
            return true;
        }
        else {
            return false;
        }
    }
    private static String readASCIIImage(String filePath) {
        try {
            byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
            return new String(encodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
