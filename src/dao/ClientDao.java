package dao;

import model.Client;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ClientDao {
    private Connection connection;

    public ClientDao() {
        connection = DatabaseConnection.getConnection();
    }

    public void ajouterClient(Client c) {
        String sql = "INSERT INTO clients (id,nom,prenom,conseille_id) values(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1,c.getId());
            stmt.setString(2,c.getNom());
            stmt.setString(3,c.getPrenom());
            stmt.setObject(4,c.getConseille_id());
            stmt.executeUpdate();
        } catch (SQLException E) {
            System.out.println("erreur : " + E.getMessage());
        }
    }
    public void supprimerClient(UUID id){
        String sql = "delete from clients where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            stmt.executeQuery();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
