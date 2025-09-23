package model;

import Enum.TypeContrat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class Contrat {
    private UUID id;
    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private HashMap<Integer,String> siniters;

    public Contrat(UUID id, TypeContrat typeContrat, LocalDate dateDebut, LocalDate dateFin, HashMap<Integer, String> siniters) {
        this.id = id;
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.siniters = siniters;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
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

    public HashMap<Integer, String> getSiniters() {
        return siniters;
    }

    public void setSiniters(HashMap<Integer, String> siniters) {
        this.siniters = siniters;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", typeContrat=" + typeContrat +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", siniters=" + siniters +
                '}';
    }
}
