package dao;

import model.Conseille;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
    public void supprimerConseiller(UUID id){
        String sql = "DELETE FROM conseilles WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public  Conseille findById(UUID id){
        String sql = "SELECT * FROM conseilles where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Conseille(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        (UUID) rs.getObject("id")
                );
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
