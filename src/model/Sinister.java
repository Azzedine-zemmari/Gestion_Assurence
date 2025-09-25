package model;

import Enum.TypeSinister;

import java.time.LocalDate;
import java.util.UUID;

public class Sinister {
    private UUID id;
    private TypeSinister typeSinister;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double montant;
    private String description;
    private UUID contrat_Id;

    public Sinister(UUID id, TypeSinister typeSinister, LocalDate dateDebut, LocalDate dateFin, Double montant, String description,UUID contrat_Id) {
        this.id = id;
        this.typeSinister = typeSinister;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.description = description;
        this.contrat_Id = contrat_Id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeSinister getTypeSinister() {
        return typeSinister;
    }

    public void setTypeSinister(TypeSinister typeSinister) {
        this.typeSinister = typeSinister;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getContrat_Id() {
        return contrat_Id;
    }

    public void setContrat_Id(UUID contrat_Id) {
        this.contrat_Id = contrat_Id;
    }

    @Override
    public String toString() {
        return "Sinister{" +
                "id=" + id +
                ", typeSinister=" + typeSinister +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", montant=" + montant +
                ", description='" + description + '\'' +
                ", contrat_Id=" + contrat_Id +
                '}';
    }
}
