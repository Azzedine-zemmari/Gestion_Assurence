package service;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import dao.ClientDao;
import dao.ConseilleDao;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {
    private final ClientDao clientDao;
    private final ConseilleDao conseilleDao = new ConseilleDao();
    public ClientService(){
        this.clientDao = new ClientDao();
    }
    public static boolean isValidEmail(String email){
        //todo: document more about it
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void ajouterClient(Client c){
        if(c.getNom() == null || c.getNom().isEmpty()){
            System.out.println("ERREUR NOM NE PEUT PAS ETRE VIDE");
            return;
        }
        if(c.getPrenom() == null || c.getPrenom().isEmpty()){
            System.out.println("ERREUR PRENOM NE PEUT PAS ETRE VIDE");
            return;
        }
        if(c.getEmail() == null || !isValidEmail(c.getEmail())){
            System.out.println(c.getEmail());
            return;
        }
        if(conseilleDao.findById(c.getConseille_id()) == null){
            System.out.println("ERREUR ce conseille et n existe pas");
            return;
        }
        clientDao.ajouterClient(c);
        System.out.println("Client ajouter avec succes");
    }
    public void supprimerClient(UUID id){
        if(id == null){
            System.out.println("Erreur id ne peut pas etre vide");
            return ;
        }
        int row = clientDao.supprimerClient(id);
        if(row > 0){
        System.out.println("Client supprimer avec success ");
        }else{
            System.out.println("Aucune Client supprimer");
        }
    }
    public List<String> rechercherClientsParNom(String nom){
        if(nom == null || nom.isEmpty()){
            System.out.println("Erreur nom ne peut pas etre vide");
            return new ArrayList<>();
        }
        return clientDao.rechercherClientParNom(nom);
    }
    public Optional<Client> rechercherClientParId(UUID id){
        Client client = clientDao.rechercherParId(id);
        return Optional.ofNullable(client);
    }
}
