package controller.requests;

import controller.Dispatcher;
import model.gamemodes.Gamemodable;
import model.player.Player;
import model.round.Round;
import java.util.List;

/**
 * Represents a request for adding the bets that the players selected to the current gamemode.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class SetBetAmountRequest extends Request{
    List<Integer> betsSelected;

    /**
     * Creates a {@code SetBetAmountRequest} which will use the bets provided in the current gamemode when a player
     * answers.
     * @param betsSelected the bets that the player selected before the question is shown as {@code List<Integer>}
     */
    public SetBetAmountRequest(List<Integer> betsSelected) {
        this.betsSelected = betsSelected;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        Round currentRound = model.getRound(Request.roundId);
        Gamemodable currentGamemode = currentRound.getGamemode();
        int playerIndex = 0;
        Player currentPlayer;
        for(Integer betAmount:betsSelected) {
            currentPlayer = model.getPlayers().get(playerIndex);
            if (betAmount > currentPlayer.getScore())
                betAmount = currentGamemode.getMinBet(); // TODO MAKE METHOD FOR INVOKING GENERAL PRE QUESTION ACTIONS
            currentGamemode.setBetAmount(betAmount, playerIndex);
            playerIndex++;
        }
    }
}
