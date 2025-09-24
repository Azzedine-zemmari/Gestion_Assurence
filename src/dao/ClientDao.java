package dao;

import model.Client;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    public int supprimerClient(UUID id){
        String sql = "delete from clients where id = ?";
        int row = 0;
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            row = stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
        return row;
    }
    public ArrayList<Client> afficherAllclient(){
        ArrayList<Client> clients = new ArrayList<>();
        String sql = "select * from clients";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                clients.add(new Client(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        (UUID) rs.getObject("id"),
                        (UUID) rs.getObject("conseille_id")
                ));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return clients;
    }
}
