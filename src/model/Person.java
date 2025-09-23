package model;

public abstract class Person {
    protected String nom ;
    protected String prenom;
    protected String email;

    public Person(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return  this.prenom;
    }
    public String getEmail(){
        return this.email;
    }
}

