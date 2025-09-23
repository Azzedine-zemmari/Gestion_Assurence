package dao;

import model.Conseille;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConseilleDao {
    private Connection connection;
    public ConseilleDao(){
        this.connection = DatabaseConnection.getConnection();
    }
    public void ajouterConseiller(Conseille c){
        String sql = "INSERT INTO conseilles(id,nom,prenom,email) VALUES(?,?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,c.getId());
            stmt.setString(2,c.getNom());
            stmt.setString(3,c.getPrenom());
            stmt.setString(4,c.getEmail());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
