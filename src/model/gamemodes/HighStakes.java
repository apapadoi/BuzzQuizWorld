package model.gamemodes;

import model.Model;

import java.util.HashMap;

/**
 * This class represents the gamemode High Stakes. At the start the question's category is shown and the player bets an amount of points. If he answers
 * correct he earns this amount of points, otherwise he loses them.
 * @author Tasos Papadopoulos
 * @author Thodwrhs Myridis
 * @version 5.12.2020
 * */
public class HighStakes extends Gamemode {
    private final HashMap<Integer, Integer> bets;

    /**
     * Default constructor.
     */
    public HighStakes() {
        super("At first the category of the question is shown.\nEach player places his bet.\n" +
                "Then, the question is shown, each player answers and if he answered correctly\n" +
                "he earns his bet, otherwise he loses his bet.\n" +
                "Available bets: 250,500,750,1000\n" +
                "If player's points get under 250 automatically the game bets all of his points.\n",15);
        this.bets = new HashMap<>(2);
        for(int i=0;i<Model.getInstance().getMaxPlayers();i++)
            bets.put(i, 250);
    }

    /**
     * @see Gamemode
     */
    @Override
    public String toString() { return "High Stakes";}

    /**
     * Returns the min bet for this gamemode.
     * @return the min bet value as {@code int}
     */
    @Override
    public int getMinBet() {
        return 250;
    }

    /**
     * If the players answers correct update his score using the model component.
     * @see Gamemode
     */
    @Override
    public void actionIfCorrectAnswer(Model model,int millis, int playerIndex) {
        model.addScore(bets.get(playerIndex), playerIndex);
    }


    /**
     * Checks if player has 0 score and if he has, adds 250 points so he can bet.
     * @param model instance of {@code Model} class
     */
    @Override
    public void checkZeroScoreAndUpdate(Model model, int playerIndex) {
        if (model.getScore(playerIndex)<=0) {
            model.addScore(-1*model.getScore(playerIndex)+250, playerIndex);
        }
    }

    /**
     * The High Stakes gamemode has a pre-question format so method returns true.
     * @see Gamemodable
     */
    @Override
    public boolean hasPreQuestionPhase() {
        return true;
    }

    /**
     * If the user answer wrong update his score using the {@code model} component.
     * @param model instance of {@code Model} class
     * @see Gamemode
     */
    @Override
    public void actionIfWrongAnswer(Model model, int playerIndex) {
        model.addScore(-bets.get(playerIndex), playerIndex);
    }

    /**
     * Returns the player's bet amount.
     * @return the bet amount as {@code int}
     * @param playerIndex the player's index whose bet amount will be returned as {@code int}
     */
    public int getBetAmount(int playerIndex){
        return this.bets.get(playerIndex);
    }

    /**
     * Sets the bet amount of the player
     * @param betAmount the new bet amount
     */
    @Override
    public void setBetAmount(int betAmount, int playerIndex){
        bets.put(playerIndex, betAmount);
    }
}
