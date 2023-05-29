package hra;

import java.io.*;

public class Kikinovysoubory {
    private String name;

    public Kikinovysoubory(String name) {
        this.name = name;
    }

    public void readScore(){
        String score;
        int i=0;
        try (BufferedReader reader=new BufferedReader(new FileReader(name))) {
            while(true){
                score=reader.readLine();
                if(score==null||i==3){
                    break;
                }
                i++;
                System.out.println(i+". "+score);
            }
        }catch (IOException error){
            System.out.println(error.getMessage());
        }
    }

    public void writeScore(int score,String playername){
        String[] arrayofData;
        Filescores[] players = new Filescores[countOfScores()+1];
        players[0] = new Filescores(playername,score);

        try (BufferedReader reader=new BufferedReader(new FileReader(name))){
            for(int i=1;i<players.length;i++){
                arrayofData = reader.readLine().split(";");
                players[i]=new Filescores(arrayofData[1],Integer.parseInt(arrayofData[0]));
            }
        }catch (IOException error){
            System.out.println(error.getMessage());
        }

        Filescores pomocna = new Filescores("",0);
        for(int i=0;i<players.length-1;i++){
            for(int j=0;j<players.length-1;j++){
                if(players[j].getScore()<players[j+1].getScore()){
                    pomocna=players[j];
                    players[j]=players[j+1];
                    players[j+1]=pomocna;
                }
            }
        }


        try (BufferedWriter writer=new BufferedWriter(new FileWriter(name))){
            for (int i=0;i<players.length;i++){
                writer.write(players[i].getScore()+";"+players[i].getName()+"\n");
            }
        }catch (IOException error){
            System.out.println(error.getMessage());
        }


    }

    private int countOfScores(){
        int count=0;
        String score;
        try (BufferedReader reader=new BufferedReader(new FileReader(name))){
            while(reader.readLine()!=null){
                count++;
            }
        }catch (IOException error){
            System.out.println(error.getMessage());
        }
        return count;
    }
}
