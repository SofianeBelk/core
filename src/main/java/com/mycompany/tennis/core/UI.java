package com.mycompany.tennis.core;

import com.mycompany.tennis.core.controller.JoueurController;

public class UI {
    public static void main(String[] args) {
        JoueurController joueurController = new JoueurController();
        joueurController.afficheDetailJoueur();
    }
}
