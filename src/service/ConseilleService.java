package service;

import dao.ConseilleDao;
import model.Conseille;

import java.util.NoSuchElementException;

public class ConseilleService {
    private final ConseilleDao conseilleDao;
    public ConseilleService(){
        this.conseilleDao = new ConseilleDao();
    }
    public void ajouterConseiller(Conseille c){
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
        conseilleDao.ajouterConseiller(c);
        System.out.println("Conseiller ajouter avec success");
    }
    public void supprimerConseiller(Conseille c){
        if(conseilleDao.findById(c.getId()) == null){
            throw new NoSuchElementException("Conseille with id " + c.getId() + "Not found ");
        }

        conseilleDao.supprimerConseiller(c.getId());
        System.out.println("Conseiller supprimer avec success");
    }
}
