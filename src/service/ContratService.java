package service;

import dao.ContratDao;
import model.Contrat;

import java.sql.SQLException;

public class ContratService {
    private  final ContratDao contratDao ;
    public ContratService(){
        contratDao = new ContratDao();
    }
//    SQLException because the ajouterContrat au dao need it
    public void ajouterContrat(Contrat c) throws SQLException {
        if(contratDao.ajouterContrat(c) >= 1){
            System.out.println("row inserted ");
        }
        else{
            System.out.println("no row inserted ");
        }
    }
}
