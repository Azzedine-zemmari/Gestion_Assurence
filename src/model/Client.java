package model;

import java.util.HashMap;
import java.util.UUID;

public class Client extends Person{
    private UUID id;
    private String conseille_id;

    public Client(String nom, String prenom, String email, UUID id, String conseille_id) {
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


    public String getConseille_id() {
        return conseille_id;
    }

    public void setConseille_id(String conseille_id) {
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
