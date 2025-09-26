package service;

import dao.ClientDao;
import dao.ContratDao;
import model.Contrat;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;


public class ContratService {
    private  final ContratDao contratDao ;
    private final ClientDao clientDao;
    public ContratService(){
        contratDao = new ContratDao();
        clientDao = new ClientDao();
    }
//    SQLException because the ajouterContrat au dao need it
    public void ajouterContrat(Contrat c) throws SQLException {
        if (c.getDateDebut() == null || c.getDateFin() == null) {
            throw new IllegalArgumentException("Les dates ne peuvent pas être nulles");
        }
        if (c.getDateDebut().isAfter(c.getDateFin())) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin");
        }

        // 2. Vérification du type de contrat
        if (c.getTypeContrat() == null) {
            throw new IllegalArgumentException("Le type de contrat est obligatoire");
        }

        // 3. Vérification que le client existe
        if (clientDao.rechercherParId(c.getClient_id())== null) {
            throw new IllegalArgumentException("Le client n'existe pas dans la base");
        }

        // 4. Ajout du contrat
        if (contratDao.ajouterContrat(c) < 1) {
            throw new SQLException("Échec de l'insertion du contrat");
        }
        else{
            System.out.println("contrat cree avec success");
        }
    }
    public Optional<Contrat> afficherContratParId(UUID id) throws SQLException{
        Contrat contrat = contratDao.afficherContratParId(id);
        return Optional.ofNullable(contrat);
    }
}
