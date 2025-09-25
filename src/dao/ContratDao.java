package dao;

import model.Contrat;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class ContratDao {
    private Connection connection;
    public ContratDao(){
        connection = DatabaseConnection.getConnection();
    }
    public int ajouterContrat(Contrat c) throws SQLException {
        String sql = "INSERT INTO contrats (id,client_id,type_contrat,date_debut,date_fin) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1,c.getId());
            stmt.setObject(2,c.getClient_id());
//          todo : document about java.sql.Types.OTHER
            stmt.setObject(3,c.getTypeContrat().name(), java.sql.Types.OTHER);
            // java.sql.Date.valueOf takes a localDate and turn it too java.sql.Date so the setDate will accept it
            stmt.setDate(4,java.sql.Date.valueOf(c.getDateDebut()));
            stmt.setDate(5,java.sql.Date.valueOf(c.getDateFin()));
            return stmt.executeUpdate();
    }

}
