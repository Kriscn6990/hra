package hra;

public class Map {
    private int row;
    private int col;
    private char[][] arrayMap;

    public char[][] getArrayMap() {
        return arrayMap;
    }

    public void setArrayMap(char[][] arrayMap) {
        this.arrayMap = arrayMap;
    }

    public Map(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    //vytvor mapu
    public void createMap(){
        arrayMap = new char[row][col];
        arrayMap[0][0] = '┌';
        arrayMap[0][col-1] = '┐';
        arrayMap[row-1][0] = '└';
        arrayMap[row-1][col-1] = '┘';
        arrayMap[1][1] = '#';
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(arrayMap[i][j] != '┌' && arrayMap[i][j] != '┐' && arrayMap[i][j] != '┘' && arrayMap[i][j] != '└' && arrayMap[i][j] != '#'){
                    if(i != 0 && i != row-1){
                        if( j != 0 && j != col-1){
                            arrayMap[i][j] = '.';
                        }
                        else arrayMap[i][j] = '│';
                    }
                    else arrayMap[i][j] = '─';
                }
            }
        }
    }
    //vypis mapy
    public void showMap(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arrayMap[i][j]);
            }
            System.out.println();
        }
    }

}
