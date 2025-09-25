package service;

import dao.SinisterDao;
import javafx.animation.ScaleTransition;
import model.Sinister;

import java.sql.SQLException;

public class SinisterService {
    SinisterDao sinisterDao ;
    public SinisterService(){
        this.sinisterDao = new SinisterDao();
    }

    public void ajouterSinister(Sinister s) {
        try {
            if (sinisterDao.ajouterSinister(s) >= 1) {
                System.out.println("row created ");
            } else {
                System.out.println("no row created ");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
