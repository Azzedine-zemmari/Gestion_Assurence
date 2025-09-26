package dao;

import model.Client;
import model.Contrat;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import  Enum.TypeContrat;

public class ContratDao {
    private Connection connection;
    private final static ClientDao clientDao = new ClientDao();
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

    public Contrat afficherContratParId(UUID id) throws SQLException{
        String sql = "select * from contrats where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1,id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return new Contrat(
                    (UUID) rs.getObject("id"),
                    (UUID) rs.getObject("client_id"),
                    TypeContrat.valueOf(rs.getString("type_contrat")), // from string to enum
                    rs.getDate("date_debut").toLocalDate(), // from java.sql.Date to localDate
                    rs.getDate("date_fin").toLocalDate()
            );
        }
        return null;
    }
    public void supprimerContrat(UUID id){
        String sql = "delete from contrats where id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setObject(1,id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Contrat supprimé avec succès");
            } else {
                System.out.println("Aucun contrat trouvé avec cet ID");
            }        }catch (SQLException E){
            System.out.println(E);
        }
    }
    public  List<Contrat> afficherAllContrat(){
        ArrayList<Contrat> contrats = new ArrayList<>();
        String sql = "select * from contrats";
        try(Statement stmt = connection.createStatement()){
           ResultSet rs =  stmt.executeQuery(sql);
           while(rs.next()){
               contrats.add(new Contrat(
                       (UUID) rs.getObject("id"),
                       (UUID) rs.getObject("client_id"),
                       TypeContrat.valueOf(rs.getString("type_contrat")),
                       rs.getDate("date_debut").toLocalDate(),
                       rs.getDate("date_fin").toLocalDate()
                       )
               );
           }
           return contrats;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public List<Contrat> ContratsForClient(UUID id){
        List<Contrat> contrats = afficherAllContrat();
        return contrats.stream()
                .filter(contrat -> id.equals(contrat.getClient_id()))
                .collect(Collectors.toList());
    }
}
