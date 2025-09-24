package service;

import dao.ClientDao;
import dao.ConseilleDao;
import model.Client;

import java.util.UUID;

public class ClientService {
    private final ClientDao clientDao;
    private final ConseilleDao conseilleDao = new ConseilleDao();
    public ClientService(){
        this.clientDao = new ClientDao();
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
        if(c.getEmail() == null || c.getEmail().isEmpty()){
            System.out.println("ERREUR EMAIL NE PEUT PAS ETRE VIDE");
            return;
        }
        if(conseilleDao.findById(c.getConseille_id()) == null){
            System.out.println("ERREUR ce conseille et n existe pas");
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
}
