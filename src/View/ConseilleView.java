package View;

import model.Conseille;
import model.Contrat;
import service.ConseilleService;

import java.util.Scanner;
import java.util.UUID;

public class ConseilleView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ConseilleService conseilleService = new ConseilleService();
    public static void start(){
        System.out.println("1 . cree conseiller ");
        System.out.println("2 . delete client ");
        System.out.println("3 . recherche un client par nom ");
        System.out.println("4 . rchecher par id ");
        System.out.println("5 . afficher liste du client ");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix){
            case 1 : creeConseiller();break;
            default:
                System.out.println("ghlaaaaaaaaat");
                break;
        }

    }
    public static void creeConseiller(){
        System.out.println("Enter votre nom ");
        String nom = scanner.nextLine();
        System.out.println("Enter votre prenom ");
        String prenom = scanner.nextLine();
        System.out.println("Enter votre email ");
        String email = scanner.nextLine();

        Conseille c = new Conseille(nom,prenom,email,UUID.randomUUID());
        conseilleService.ajouterConseiller(c);
    }
}
