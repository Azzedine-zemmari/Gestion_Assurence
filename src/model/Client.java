package model;

import java.util.HashMap;
import java.util.UUID;

public class Client extends Person{
    private UUID id;
    private UUID conseille_id;

    public Client(String nom, String prenom, String email, UUID id, UUID conseille_id) {
        super(nom, prenom, email);
        this.id = id;
        this.conseille_id = conseille_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getConseille_id() {
        return conseille_id;
    }

    public void setConseille_id(UUID conseille_id) {
        this.conseille_id = conseille_id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", conseille=" + conseille_id +
                '}';
    }


}
