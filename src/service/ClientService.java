package service;

import dao.ClientDao;
import model.Client;

public class ClientService {
    private final ClientDao clientDao;
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
        if(c.getConseille_id() == null || c.getConseille_id().isEmpty()){
            System.out.println("ERREUR Conseille id NE PEUT PAS ETRE VIDE");
            return;
        }
        clientDao.ajouterClient(c);
        System.out.println("Client ajouter avec succes");

    }
}
