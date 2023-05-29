package hra;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;


public class Main {
    public static void main(String[] args) {
        String redColor = "\u001B[31m";

        // ANSI escape sequence to reset the color
        String resetColor = "\u001B[0m";
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        String checkinputvalueforname;//ziskej jmeno pro hrdinu
        Scanner input=new Scanner(System.in);
        Kikinovysoubory scores = new Kikinovysoubory("Score.txt");
        System.out.println("Skore lidi:");
        scores.readScore();
        System.out.println("Vitejte ve hre superhero\n prosim zvolte si sveho hrdinu\n batman nebo spiderman:");
        String superhero=input.nextLine();
        System.out.println("Napiste svoji prezdivku:");
        checkinputvalueforname = input.nextLine();
        System.out.println(redColor + "Pro lepsi funkcnost a zazitek nepiste do prikazoveho radku" + resetColor);
        System.out.println("stisknete libovolnou klavesu...");
        input.nextLine();
        //tvorba hrace a mapy
        Player player = new Player(checkinputvalueforname,10,1);
        Map firstmap = new Map(10);

        //vypis hrdiny
        String asciiImagespider = readASCIIImage("ascii_art_spider.txt");
        String asciiImagebatman = readASCIIImage("ascii_art.txt");
        String asciiImageGarfield = readASCIIImage("garfield.txt");
        String asciiImageDefault = readASCIIImage("ascii-art_default.txt");
        String asciiMap= readASCIIImage("Map.txt");
        String asciiMap2= readASCIIImage("Map2.txt");
        String asciiMap3=readASCIIImage("Map3.txt");
        String [] asciiMaps={asciiMap,asciiMap2,asciiMap3};


        Random generator=new Random();
        int [] position=new int[2];
        position[0]=1;
        position[1]=1;
        int [] lastposition = {1,1};
        char x =' ';
        //firstmap.createMap(); generace mapy
        if(superhero.equals("batman")){
            System.out.println(asciiImagebatman);
        }
        else if (superhero.equals("spiderman")) {
            System.out.println(asciiImagespider);
        }
        else{
            System.out.println(asciiImageDefault);
        }

        firstmap.createMapByString(asciiMaps[generator.nextInt(asciiMaps.length)]);
        //mapa
        do{
            if(player.getHp() > 0) {
                showplayerinfo(player.getHp(), player.getName(), player.getScore(), player.getAttack());
                firstmap.showMap();

                for (int i = 0; i < firstmap.getHp().length; i++) {
                    if (onItem(position, firstmap.getHp()[i].getItemPosition())) {
                        System.out.println("Byl ziskan item");
                        System.out.println("Pridava " + firstmap.getHp()[i].getCountOfHealthToAdd() + " \u2665");
                        player.setHp(player.getHp() + firstmap.getHp()[i].getCountOfHealthToAdd());
                        int[] changePos = {100, 100};
                        firstmap.getHp()[i].setItemPosition(changePos);//presunuti itemu na nedosazitelnou pozici
                        firstmap.setCountOfmarks(firstmap.getCountOfmarks()-1);
                    }
                }
                for (int i = 0; i < firstmap.getEnemies().length; i++) {
                    int figtresult;
                    if (onItem(position, firstmap.getEnemies()[i].getItemPosition())) {
                        System.out.println(asciiImageGarfield);
                        Fight fight = new Fight();
                        figtresult = fight.begin(firstmap.getEnemies()[i].getHp(), firstmap.getEnemies()[i].getAttack(), firstmap.getEnemies()[i].getName(),player, firstmap.getEnemies()[i]);
                        if(figtresult == 2) {
                            char[][] Editedmap = firstmap.getArrayMap();
                            Editedmap[firstmap.getEnemies()[i].getItemPosition()[0]][firstmap.getEnemies()[i].getItemPosition()[1]] = '?';
                            Editedmap[lastposition[0]][lastposition[1]] = '#';
                            position = lastposition.clone();
                            firstmap.setArrayMap(Editedmap);
                            //pokud hrac zada moznost 3, utekl
                        }
                        //showEnemyInfo(firstmap.getEnemies()[i].getHp(), firstmap.getEnemies()[i].getAttack(), firstmap.getEnemies()[i].getName());
                        if(player.getHp()>0) {
                            if(player.getAttack()!=3 && firstmap.getEnemies()[i].getHp() <= 0)player.setAttack(player.getAttack()+1);
                            showplayerinfo(player.getHp(), player.getName(), player.getScore(),player.getAttack());
                            firstmap.showMap();
                        }
                        if (figtresult != 2) {
                            int[] changePos = {100, 100};
                            firstmap.getEnemies()[i].setItemPosition(changePos);
                            firstmap.setCountOfmarks(firstmap.getCountOfmarks()-1);
                        }

                    }
                }
                if(player.getHp()>0) {
                    System.out.println("Pohyb: WASD/wasd");
                    System.out.println("Konec: 0");
                    System.out.println("Pocet zbyvajicich itemu v mape:" + firstmap.getCountOfmarks());
                    x = listenforkey();//metoda pro cteni bez enteru
                    lastposition = position.clone();
                    walk(firstmap.getArrayMap(), position, x);
                    for (int i = 0; i < 20; i++) {
                        System.out.println();
                    }
                }
                if(player.getHp()>0&&firstmap.getCountOfmarks()==0){
                    System.out.println("Byl jste presunut do dalsi mapy");
                    position[0]=1;
                    position[1]=1;
                    lastposition=position.clone();
                    firstmap.setCountOfmarks(10);
                    firstmap.createMapByString(asciiMap);
                }
            } else {
                x = '0';
            }
        }while(x!='0');
        System.out.println(redColor +"Zemrel jste" + resetColor);
        System.out.println("Skore: " + player.getScore());
        scores.writeScore(player.getScore(),player.getName());
        System.out.println("Skore lidi:");
        scores.readScore();
    }
    public static void walk(char [][] map, int [] position, char x) {
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
    public static void showplayerinfo(int hp,String name,int score,int attack){
        System.out.println("Hracska statistika");
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
            if(i==99) System.out.println();
        }

        System.out.print("HP " + hp);
        System.out.print("[");
        for (int i = 0; i < hp; i++) {
            System.out.print("\u2665");

        }
        System.out.print("]");
        System.out.print("\tSila " + attack);
        System.out.print("[");
        for (int i = 0; i < attack; i++) {
            System.out.print("\u2694");

        }
        System.out.print("]");
        System.out.print("\tSuper hrdina -> " + name + "\t");
        System.out.println("\tSkore -> " + score + "\t");
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
            if(i==99) System.out.println();
        }

    }

    public static boolean onItem(int [] position, int [] itemPosition){
        return position[0] == itemPosition[0] && position[1] == itemPosition[1];
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
    private static char listenforkey(){
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
