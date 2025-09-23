package View;

import dao.ConseilleDao;
import model.Conseille;
import model.Contrat;
import service.ConseilleService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConseilleView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ConseilleService conseilleService = new ConseilleService();
    private static final ConseilleDao conseilleDao = new ConseilleDao();
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
            case 2 : supprimerConseiller();break;
            case 3 : rechercherConseiller();break;
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
    public static void supprimerConseiller(){
       List<Conseille> conseilles =  conseilleDao.afficherAllConseiller();
       conseilles.stream()
               .forEach(conseille -> System.out.println(conseille));
        System.out.println("Enterer id pour le conseillent ");
        String Conseille_idS = scanner.nextLine();
        UUID uuid = UUID.fromString(Conseille_idS);
        conseilleService.supprimerConseiller(uuid);
    }
    public static void rechercherConseiller(){
        List<Conseille> conseilles = conseilleDao.afficherAllConseiller();
        conseilles.stream()
                .forEach(conseille -> System.out.println(conseille));
        System.out.println("Enterer id pour le conseillent ");
        String Conseille_idS = scanner.nextLine();
        UUID uuid = UUID.fromString(Conseille_idS);
        System.out.println(conseilleDao.findById(uuid));
    }
}
