package View;

import dao.ClientDao;
import model.Client;
import service.ClientService;

import java.util.Scanner;
import java.util.UUID;

public class ClientView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientService();
    public static void start(){
        System.out.println("1 . cree client ");
        System.out.println("2 . delete client ");
        System.out.println("3 . recherche un client par nom ");
        System.out.println("4 . rchecher par id ");
        System.out.println("5 . afficher liste du client ");

        int choix = scanner.nextInt();

//        switch (choix){
//
//        }

    }
    public void creeeClient(){
        System.out.println("Enter votre nom ");
        String nom = scanner.nextLine();
        System.out.println("Enter votre prenom ");
        String prenom = scanner.nextLine();
        System.out.println("Enter votre email ");
        String email = scanner.nextLine();
        UUID conseille_id = UUID.randomUUID();

//        Client c = new Client(nom,prenom,email,UUID.randomUUID(),conseille_id);


    }
}
