package hra;

import java.util.Random;

public class Map {
    private int countOfmarks;
    private static int row = 7;
    private static int col = 25;
    private char[][] arrayMap;
    private Hp[] hp;
    private Enemies [] enemies;

    public char[][] getArrayMap() {
        return arrayMap;
    }
    public int getCountOfmarks() {
        return countOfmarks;
    }
    public void setCountOfmarks(int countOfmarks) {
        this.countOfmarks = countOfmarks;
    }
    public void setArrayMap(char[][] arrayMap) {
        this.arrayMap = arrayMap;
    }
    public Map(int countOfmarks) {
        this.countOfmarks = countOfmarks;
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
        Random generator = new Random();
        int ccount = countOfmarks;
        int countQMarks=0;
        arrayMap = new char[row][col];
        arrayMap[0][0] = '┌';
        arrayMap[0][col-1] = '┐';
        arrayMap[row-1][0] = '└';
        arrayMap[row-1][col-1] = '┘';
        arrayMap[1][1] = '#';//vychozi pozice hrace
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
        arrayMap = addmarks(countOfmarks,row,col,arrayMap);
        createItems(arrayMap,countOfmarks);
    }
    //vypis mapy
    public void showMap(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arrayMap[i][j]);
            }
            if(i == 0) System.out.print("\t Legenda: Hrac [#], predmet [?], stena [|]");
            System.out.println();
        }
    }
    //pridej otazniky do mapy
    public char[][] addmarks(int count,int row,int col,char[][] arrayMap){
        //7 radku pouzitelne 5 25 radku pouzitelne 23
        Random generate = new Random();
        int rw,cl;
        while (count != 0)
        {

            rw = generate.nextInt(1,row-1);
            cl = generate.nextInt(1,col-1);

            if(arrayMap[rw][cl] == '.'){
                arrayMap[rw][cl] = '?';
                count--;
            }

        }
        return arrayMap;
    }

    public void createItems(char [][] arrayMap,int countQMarks){
        Random generator = new Random();
        int countHp=0;
        int countEnemies=0;
        int [][] positionOfHp=new int[countQMarks][2];
        int [][] positionOfEnemies=new int[countQMarks][2];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(arrayMap[i][j]=='?'){
                    if(generator.nextInt(0,3)>1){
                        positionOfHp[countHp][0]=i;
                        positionOfHp[countHp][1]=j;
                        countHp++;
                    }
                    else{
                        positionOfEnemies[countEnemies][0]=i;
                        positionOfEnemies[countEnemies][1]=j;
                        countEnemies++;
                    }
                }
            }
        }
        hp=new Hp[countHp];
        enemies=new Enemies[countEnemies];

        for(int i=0;i<countHp;i++){
            hp[i]=new Hp();
            hp[i].setItemPosition(positionOfHp[i]);
            hp[i].setCountOfHealthToAdd(generator.nextInt(1,3));
        }
        for(int i=0;i<countEnemies;i++){
            enemies[i]=new Enemies();
            enemies[i].setItemPosition(positionOfEnemies[i]);
            enemies[i].setHp(generator.nextInt(1,4));
            enemies[i].setAttack(generator.nextInt(1,3));
            if(enemies[i].getHp()<=2&&enemies[i].getAttack()<=1){
                enemies[i].setDifficulty("Lehky");
            }
            else if (enemies[i].getHp()<=4||(enemies[i].getHp()>4&&enemies[i].getAttack()==1)){
                enemies[i].setDifficulty("Stredni");
            }
            else{
                enemies[i].setDifficulty("Tezky");
            }
        }

    }

    public void createMapByString(String map){
        arrayMap=new char[row][col];
        String [] lines=map.split("\r?\n");
        for(int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                arrayMap[i][j]=lines[i].charAt(j);
            }
        }
        arrayMap = addmarks(countOfmarks,row,col,arrayMap);
        createItems(arrayMap,countOfmarks);
    }

    public Hp[] getHp() {
        return hp;
    }

    public void setHp(Hp[] hp) {
        this.hp = hp;
    }

    public Enemies[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemies[] enemies) {
        this.enemies = enemies;
    }
}
