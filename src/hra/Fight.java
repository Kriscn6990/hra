package hra;

import java.util.Scanner;

public class Fight {

    public int fightMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("Volby:");
        System.out.println("1. Utok");
        System.out.println("2. Silnejsi utok (mensi sance ze se provede)");
        return input.nextInt();
    }


}
