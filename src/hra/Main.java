package hra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Map firstmap = new Map(7,7,25);
        int [] position=new int[2];
        position[0]=1;
        position[1]=1;
        char x;
        firstmap.createMap();
        do{
            firstmap.showMap();
            for(int i=0;i<firstmap.getHp().length;i++){
                if(onItem(position,firstmap.getHp()[i].getItemPosition())){
                    System.out.println("Vzit item: E/e");
                }
            }
            for(int i=0;i<firstmap.getEnemies().length;i++){
                if (onItem(position,firstmap.getEnemies()[i].getItemPosition())) {
                    System.out.println("Utocit na nepritele: E/e");
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
    //delete later
    public static boolean checkWall(char map[][], int row, int column) {
        if (map[row][column] == '│' || map[row][column] == '─' || map[row][column] == '┐' || map[row][column] == '┌' || map[row][column] == '└' || map[row][column] == '┘') {
            return false;
        }
        else{
            return true;
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
}
