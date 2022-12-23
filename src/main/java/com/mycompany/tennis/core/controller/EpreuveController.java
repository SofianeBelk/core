package com.mycompany.tennis.core.controller;

import java.util.Scanner;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.service.EpreuveService;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController() {
        this.epreuveService = new EpreuveService();
    }

    public void afficheDetailsEpreuve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ?");
        Long identifiant = scanner.nextLong();
        Epreuve epreuve = epreuveService.getEpreuve(identifiant);

    }
}
