package model;

import Enum.TypeContrat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class Contrat {
    private UUID id;
    private UUID client_id;
    private TypeContrat typeContrat;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Contrat(UUID id, UUID client_id ,TypeContrat typeContrat, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.client_id = client_id;
        this.typeContrat = typeContrat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", typeContrat=" + typeContrat +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", client_id=" + client_id   +
                '}';
    }
}
