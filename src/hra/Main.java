package hra;

public class Main {
    public static void main(String[] args) {

    }
    public static void walk(char map[][], char position[], char x) {
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
