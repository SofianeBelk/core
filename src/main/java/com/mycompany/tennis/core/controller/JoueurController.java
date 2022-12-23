package com.mycompany.tennis.core.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController() {
        this.joueurService = new JoueurService();
    }

    public void afficheDetailJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle est l'identifiant du joueur dont vous voulez affichir les informations ?");
        long identifiant = scanner.nextLong();
        scanner.close();
        Joueur joueur = this.joueurService.getJoueur(identifiant);
        System.out.println("Le joueur séléctionné s'appelle : " + joueur.getPrenom() + " " + joueur.getNom());
    }

    public void creerJoueur() {
        Joueur joueur = new Joueur();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle est le nom du joueur : ");
        String nom = scanner.nextLine();
        System.out.println("Quelle est le prénom du joueur : ");
        String prenom = scanner.nextLine();
        System.out.println("Quelle est le sexe du joueur : ");
        char sexe = scanner.nextLine().charAt(0);
        scanner.close();

        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);

        joueurService.createJoueur(joueur);
        System.out.println("Le joueur a été générer :" + joueur.getId());
    }

    public void renommeJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle est l'identifiant du joueur dont vous voulez modifier le nom ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quelle est le nouveau nom du joueur ?");
        String newName = scanner.nextLine();
        scanner.close();
        this.joueurService.renomme(identifiant, newName);
    }

    public void supprimeJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quelle est l'identifiant du joueur dont vous voulez supprimer ?");
        long identifiant = scanner.nextLong();
        scanner.close();
        this.joueurService.deleteJoueur(identifiant);
    }
}
