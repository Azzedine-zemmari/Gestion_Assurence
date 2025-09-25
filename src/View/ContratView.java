package View;

import dao.ClientDao;
import dao.ConseilleDao;
import dao.ContratDao;
import model.Client;
import model.Contrat;
import service.ClientService;
import service.ContratService;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import  Enum.TypeContrat;
import sun.util.resources.LocaleData;

public class ContratView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContratService contratService = new ContratService();
//    private static final ConseilleDao conseilleDao = new ConseilleDao();
    private static final ClientDao clientDao = new ClientDao();

    public static void start() {
        System.out.println("1 . cree contrat ");
        System.out.println("2 . delete contrat ");
//       use Optional
        System.out.println("3 . recherche un contrat par id ");
        System.out.println("4 . afficher contrat pour un client id ");

        int choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                ajouterContrat();
                break;
            case 2:
                recherParId();
                break;
            case 3:
                break;
            case 4 :
                break;
            default:
                System.out.println("choose a nombre in the list ");
        }

    }
    public static void ajouterContrat(){
        System.out.println("Entrer votre client_id");
        ArrayList<Client> clients = clientDao.afficherAllclient();
        for(Client c: clients){
            System.out.println(c);
        }
        String id = scanner.nextLine();
        UUID uuid = UUID.fromString(id);

        System.out.println("Entrer votre type du contrat : ");
        for(int i = 0; i<TypeContrat.values().length;i++){
            System.out.println(i+1 + " " + TypeContrat.values()[i]);
        }
        Integer choice = scanner.nextInt();
        scanner.nextLine();
        TypeContrat type = TypeContrat.values()[choice-1];

        System.out.println("Entrer a date debut : (yyyy-MM-dd)");
        String inputDateDebut = scanner.nextLine();
        LocalDate dateDebut = LocalDate.parse(inputDateDebut, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Entrer a date fin : (yyyy-MM-dd)");
        String inputDateFin = scanner.nextLine();
        LocalDate dateFin = LocalDate.parse(inputDateFin,DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Contrat contrat = new Contrat(UUID.randomUUID(),uuid,type,dateDebut,dateFin);
// to handle the SqlException
        try{
        contratService.ajouterContrat(contrat);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static void recherParId(){
        System.out.println("Entrer Id : ");
        String id = scanner.nextLine();
        UUID uuid = UUID.fromString(id);

        try{
        Optional<Contrat> contrat = contratService.afficherContratParId(uuid);
        if(contrat.isPresent()){
            System.out.println(contrat.get()); // to get only the contrat without Optional[]
        }
        else{
            System.out.println("No contrat with this  id ");
        }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
