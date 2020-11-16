package controller;

import model.gamemodes.OnePlayerGamemodes;

/**
 * This class contains the main method that program starts from.
 * @author Tasos Papadopoulos
 * @version 16.11.2020
 * */
public class BuzzApp {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.getView().printIntroPage();
        controller.getView().printNumOfPlayersAvailablePage();
        controller.getView().printAvailableGamemodeChoices(OnePlayerGamemodes.getAvailableGamemodes());
        controller.readGamemodeChoice();
        controller.readNumOfRoundsChoice();
    }
}
