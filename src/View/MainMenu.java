package View;


import com.sun.media.jfxmediaimpl.HostUtils;

import java.util.Scanner;

public class MainMenu {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void start(){
        int choix = -1;
        while(choix !=0){
        System.out.println("1 . gerer client : ");
        System.out.println("2 . gerer conseiller ");
        System.out.println("3 . gerer contrat ");
        System.out.println("4 . gerer sinister ");
        System.out.println("0. Quitter ");
        choix = SCANNER.nextInt();
        SCANNER.nextLine();

        switch (choix){
            case 1: ;ClientView.start();break;
            case 2 : ConseilleView.start();break;
            case 3 : ContratView.start();break;
            case 4 : SinisterView.start();break;
            case 0 :
                System.out.println("Au revoir ");break;
            default:
                System.out.println("Atterntion choisi un nombre dans la liste ");
        }
        }
    }
}
