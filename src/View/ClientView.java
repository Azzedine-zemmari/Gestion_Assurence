package View;

import dao.ClientDao;
import dao.ConseilleDao;
import model.Client;
import model.Conseille;
import service.ClientService;

import java.util.*;

public class ClientView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ClientService CLIENT_SERVICE = new ClientService();
    private static final ConseilleDao CONSEILLE_DAO = new ConseilleDao();
    private static final ClientDao CLIENT_DAO = new ClientDao();

    public static void start() {
        int choix = -1;
        while(choix != 0){

        System.out.println("1 . cree client ");
        System.out.println("2 . delete client ");
        System.out.println("3 . recherche un client par nom ");
        System.out.println("4 . rchecher par id ");
        System.out.println("5 . afficher liste du client a partient d un conseille ");
        System.out.println("0 . Quitter ");

        choix = SCANNER.nextInt();
        SCANNER.nextLine();

        switch (choix) {
            case 1:
                creeeClient();
                break;
            case 2:
                supprimerClient();
                break;
            case 3:
                afficherClientOfConseil();
                break;
            case 4 :
                rechercheParNom();
                break;
            case 5:
                rechercheParId();
                break;
            case 0:
                System.out.println("au revoir");
                break;
            default:
                System.out.println("choose a nombre in the list ");
        }}
    }

    public static void creeeClient() {
        System.out.println("Enter votre nom ");
        String nom = SCANNER.nextLine();

        System.out.println("Enter votre prenom ");
        String prenom = SCANNER.nextLine();

        System.out.println("Enter votre email ");
        String email = SCANNER.nextLine();

        System.out.println("Choisi votre conseiller");
        List<Conseille> conseilles = CONSEILLE_DAO.afficherAllConseiller();
        conseilles.stream().forEach(conseille -> System.out.println(conseille));
        String uuid = SCANNER.nextLine();
        UUID conseillerId = UUID.fromString(uuid);

        Client c = new Client(nom, prenom, email, UUID.randomUUID(), conseillerId);
        CLIENT_SERVICE.ajouterClient(c);
    }

    public static void supprimerClient(){
        ArrayList<Client> clients = CLIENT_DAO.afficherAllclient();
        for(Client c : clients){
            System.out.println(c);
        }
        System.out.println("Entrer id du client : ");
        String id = SCANNER.nextLine();
        UUID idClient = UUID.fromString(id);
        CLIENT_SERVICE.supprimerClient(idClient);
    }
    public static void afficherClientOfConseil(){
        System.out.println("test");
        List<Conseille> conseilles = CONSEILLE_DAO.afficherAllConseiller();
        conseilles.stream()
                .forEach(conseille -> System.out.println(conseille));
        System.out.println("Enterer id pour le conseillent ");
        String Conseille_idS = SCANNER.nextLine();
        UUID uuid = UUID.fromString(Conseille_idS);
        System.out.println(CLIENT_DAO.afficherClientForConseille(uuid));
    }
    public static void rechercheParNom(){
        System.out.println("Entrer votre nom : ");
        String nom = SCANNER.nextLine();

        System.out.println(CLIENT_SERVICE.rechercherClientsParNom(nom));
    }
    public static void rechercheParId(){
        System.out.println("Entrer Id : ");
        String id = SCANNER.nextLine();
        UUID uuid = UUID.fromString(id);

        Optional<Client> client = CLIENT_SERVICE.rechercherClientParId(uuid);
        if(client.isPresent()){
            System.out.println(client);
        }else{
            System.out.println("No client exists");
        }
    }
}
