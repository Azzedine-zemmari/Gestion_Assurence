package dao;

import model.Contrat;
import model.Sinister;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import Enum.TypeSinister;

public class SinisterDao {
    private Connection connection;
    private static final ContratDao CONTRAT_DAO = new ContratDao();
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
    public void supprimerSinister(UUID id) throws  SQLException{
        String sql = "delete from siniters where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setObject(1,id);
        stmt.executeUpdate();
    }
    public List<Sinister> getAllSiniter(){
        List<Sinister> sinisters = new ArrayList<>();
        String sql = "Select * from siniters";
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                sinisters.add(new Sinister(
                        (UUID) rs.getObject("id"),
                        TypeSinister.valueOf(rs.getString("type_siniter")),
                        rs.getDate("date_debut").toLocalDate(),
                        rs.getDate("date_fin").toLocalDate(),
                        rs.getDouble("Montant"),
                        rs.getString("Description"),
                        (UUID) rs.getObject("Contrat_id")
                ));
            }
            return sinisters;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public double calculerCoutTotalSinistresParClient(UUID id){
        List<Sinister> sinisters = getAllSiniter();
        List<Contrat> contrats = CONTRAT_DAO.afficherAllContrat();
        // create it again
        // get the contrat id for that client
        List<UUID> contratsDuClient = contrats.stream()
                .filter(contrat -> contrat.getClient_id().equals(id))
                .map(contrat -> contrat.getId())
                .collect(Collectors.toList());

        Double total = sinisters.stream()
                .filter(sinister -> contratsDuClient.contains(sinister.getContrat_Id()) )
                .mapToDouble(Sinister::getMontant)
                .sum();
        return total;
    }
    public List<Sinister> getAllSinisterTrie(){
        List<Sinister> sinisters = getAllSiniter();
         List<Sinister> result = sinisters.stream()
                .sorted(Comparator.comparingDouble(Sinister::getMontant).reversed())
                .collect(Collectors.toList());
         return result;
    }
    public List<Sinister> getAllSinisterById(UUID id){
        List<Sinister> sinisters = getAllSiniter();
        List<Contrat> contrats = CONTRAT_DAO.afficherAllContrat();

        List<UUID> contratIds = contrats.stream()
                .filter(contrat -> contrat.getClient_id().equals(id))
                .map(Contrat::getId)
                .collect(Collectors.toList());

        return sinisters.stream()
                .filter(sinister -> contratIds.contains(sinister.getContrat_Id()))
                .collect(Collectors.toList());
    }
}
