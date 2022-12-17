package com.mycompany.tennis.core.entity;

public class Joueur {
   
    private long id;
    private String nom;
    private String prenom;
    private Character sexe;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Character getSexe() {
        return this.sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    
}
