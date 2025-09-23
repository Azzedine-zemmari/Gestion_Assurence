package model;

import java.util.UUID;

public class Conseille extends Person{
    private UUID id;

    public Conseille(String nom, String prenom, String email, UUID id) {
        super(nom, prenom, email);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Conseille{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
