package com.mycompany.tennis.core;

import com.mycompany.tennis.core.controller.EpreuveController;

public class UI {
    public static void main(String[] args) {
        EpreuveController epreuveController = new EpreuveController();
        epreuveController.afficheDetailsEpreuve();
    }
}