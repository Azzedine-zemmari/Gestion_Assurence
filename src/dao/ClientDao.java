package dao;

import model.Client;
import model.Conseille;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClientDao {
    private Connection connection;
    private static final ConseilleDao conseilleDao = new ConseilleDao();

    public ClientDao() {
        connection = DatabaseConnection.getConnection();
    }

    public void ajouterClient(Client c) {
        String sql = "INSERT INTO clients (id,nom,prenom,email,conseille_id) values(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1,c.getId());
            stmt.setString(2,c.getNom());
            stmt.setString(3,c.getPrenom());
            stmt.setString(4,c.getEmail());
            stmt.setObject(5,c.getConseille_id());
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
    public List<Client> afficherClientForConseille(UUID id){
        List<Client> clients = afficherAllclient();
        List<Conseille> conseilles = conseilleDao.afficherAllConseiller();
        List<Client> result = clients.stream()
                .flatMap(client -> conseilles.stream()
                        .filter(conseille -> conseille.getId().equals(client.getConseille_id()))
                        .filter(conseille -> conseille.getId().equals(id))
                        .map(conseille -> client
                        ))
                .collect(Collectors.toList()
        );
        return result;
    }
    public List<String> rechercherClientParNom(String Nom){
        List<Client> clients = afficherAllclient();
        List<String> result = clients.stream()
                .filter(client -> client.getNom().toLowerCase().contains(Nom.toLowerCase()))
                // compartor et une interface a java pour compary 2 object ()
                // method refrence short hand of lambda expression
                // comparign need a key to sort the data
                .sorted(Comparator.comparing(Client::getNom))
                .map(client -> "NOM : " +  client.getNom() + " PRENOM : " + client.getPrenom() + " Email " +  client.getEmail())
                .collect(Collectors.toList());
        return result;
    }
//    todo : using Optional
    public Client rechercherParId(UUID id){
        String sql = "SELECT * FROM clients where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Client(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        (UUID) rs.getObject("id"),
                        (UUID) rs.getObject("conseille_id")
                );
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
