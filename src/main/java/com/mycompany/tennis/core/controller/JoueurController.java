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
        Joueur joueur = this.joueurService.getJoueur(identifiant);
        System.out.println("Le joueur séléctionné s'appelle : " + joueur.getPrenom() + " " + joueur.getNom());

    }
}
