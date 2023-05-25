package hra;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class File {
    private String name;

    public File(String name) {
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

    public void writeScore(int score){
        int [] scores=new int[countOfScores()+1];
        try (BufferedReader reader=new BufferedReader(new FileReader(name))){
            scores[0]=score;
            for(int i=1;i<scores.length;i++){
                scores[i]=Integer.parseInt(reader.readLine());

            }
        }catch (IOException error){
            System.out.println(error.getMessage());
        }

        int pomocna;
        for(int i=0;i<scores.length-1;i++){
            for(int j=0;j<scores.length-1;j++){
                if(scores[j]>scores[j+1]){
                    pomocna=scores[j];
                    scores[j]=scores[j+1];
                    scores[j+1]=pomocna;
                }
            }
        }

        //Arrays.sort(scores, Collections.reverseOrder());

        try (BufferedWriter writer=new BufferedWriter(new FileWriter(name))){
            for (int i=0;i<scores.length;i++){
                writer.write(scores[i]+"\n");
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
