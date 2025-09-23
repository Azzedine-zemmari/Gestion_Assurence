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

    public Sinister(UUID id, TypeSinister typeSinister, LocalDate dateDebut, LocalDate dateFin, Double montant, String description) {
        this.id = id;
        this.typeSinister = typeSinister;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.description = description;
    }

}
