package dao;

import model.Sinister;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SinisterDao {
    private Connection connection;
    public SinisterDao(){
        connection = DatabaseConnection.getConnection();
    }
    public int ajouterSinister(Sinister s) throws SQLException{
        String sql = "INSERT INTO siniters(id,type_siniter,date_debut,date_fin,Montant,Description,Contrat_id) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1,s.getId());
            stmt.setObject(2,s.getTypeSinister().name(), Types.OTHER);
            stmt.setDate(3,java.sql.Date.valueOf(s.getDateDebut()));
            stmt.setDate(4,java.sql.Date.valueOf(s.getDateFin()));
            stmt.setDouble(5,s.getMontant());
            stmt.setString(6,s.getDescription());
            stmt.setObject(7,s.getContrat_Id());
        return stmt.executeUpdate();

    }
}
