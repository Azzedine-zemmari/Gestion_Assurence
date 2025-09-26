package View;

import dao.SinisterDao;
import model.Sinister;
import service.SinisterService;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

import Enum.TypeSinister;

public class SinisterView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SinisterService SINISTER_SERVICE = new SinisterService();
    private static final SinisterDao SINISTER_DAO = new SinisterDao();

    public static void start() {
        int choix = -1;
        while (choix != 0) {

            System.out.println("1 . Créer un sinistre");
            System.out.println("2 . Supprimer un sinistre");
            System.out.println("3 . Calculer le coût total des sinistres par client");
            System.out.println("4 . Trier les sinistres par montant");
            System.out.println("5 . Afficher tous les sinistres d’un client");
            System.out.println("6 . Afficher les sinistres avant une date (yyyy-MM-dd)");
            System.out.println("7 . Afficher les sinistres dont le montant est supérieur à une valeur donnée");
            System.out.println("0 . Quitter");
            choix = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (choix) {
                case 1:
                    creeSinister();
                    break;
                case 2:
                    supprimerSinister();
                    break;
                case 3:
                    calculerCoutTotalSinistresParClient();
                    break;
                case 4:
                    sortSinisterByMnotant();
                    break;
                case 5:
                    getAllSinisterByClientId();
                    break;
                case 6:
                    getSinistresBeforeDate();
                    break;
                case 7:
                    getSinistresAboveMontant();
                    break;
                case 0:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("choose a nombre in the list ");
            }
        }

    }

    public static void creeSinister() {
        System.out.println("Entrer Type siniter");
        for (int i = 0; i < TypeSinister.values().length; i++) {
            System.out.println(i + 1 + " " + TypeSinister.values()[i]);
        }
        Integer choix = SCANNER.nextInt();
        TypeSinister type = TypeSinister.values()[choix - 1];
        SCANNER.nextLine();

        System.out.println("Entrer date debut : ");
        String date1 = SCANNER.nextLine();
        LocalDate dateDebut = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Entrer date fin : ");
        String date2 = SCANNER.nextLine();
        LocalDate dateFin = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Entrer montant : ");
        Double montant = SCANNER.nextDouble();
        SCANNER.nextLine();

        System.out.println("Entrer description : ");
        String description = SCANNER.nextLine();

        System.out.println("Entrer contrat id ");
        String id = SCANNER.nextLine();
        UUID uuid = UUID.fromString(id);
        Sinister sinister = new Sinister(UUID.randomUUID(), type, dateDebut, dateFin, montant, description, uuid);
        SINISTER_SERVICE.ajouterSinister(sinister);
    }

    public static void supprimerSinister() {
        System.out.println("Entrer votre id :");
        String id = SCANNER.nextLine();
        UUID uuid = UUID.fromString(id);
        try {
            SINISTER_DAO.supprimerSinister(uuid);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void calculerCoutTotalSinistresParClient() {
        System.out.println("Entrer votre id :");
        String id = SCANNER.nextLine();
        UUID uuid = UUID.fromString(id);

        System.out.println(SINISTER_DAO.calculerCoutTotalSinistresParClient(uuid));
    }

    public static void sortSinisterByMnotant() {
        System.out.println(SINISTER_DAO.getAllSinisterTrie());
    }

    public static void getAllSinisterByClientId() {
        System.out.println("Entrer votre id :");
        String id = SCANNER.nextLine();
        UUID uuid = UUID.fromString(id);
        System.out.println(SINISTER_DAO.getAllSinisterById(uuid));
    }

    public static void getSinistresBeforeDate() {
        System.out.println("Entre date (yyyy-MM-dd): ");
        String date = SCANNER.nextLine();
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(SINISTER_DAO.getSinistresBeforeDate(date1));
    }

    public static void getSinistresAboveMontant() {
        System.out.println("Entrer montant : ");
        Double montant = SCANNER.nextDouble();

        System.out.println(SINISTER_DAO.getSinistresAboveMontant(montant));
    }
}
