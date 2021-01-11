package controller.requests;

import controller.Dispatcher;
import model.gamemodes.Gamemodable;
import model.player.Player;
import model.round.Round;
import java.util.List;

public class SetBetAmountRequest extends Request{
    List<Integer> betsSelected;
    public SetBetAmountRequest(List<Integer> betsSelected) {
        this.betsSelected = betsSelected;
    }

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
