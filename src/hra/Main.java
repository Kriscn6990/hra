package hra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Map firstmap = new Map(7,14);
        int [] position=new int[2];
        position[0]=1;
        position[1]=1;
        char x;
        firstmap.createMap();
        do{
            firstmap.showMap();
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
}
