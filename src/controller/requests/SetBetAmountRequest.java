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
        Player currentPlayer = model.getPlayers().get(0);
        Gamemodable currentGamemode = currentRound.getGamemode();
        int playerIndex = 0;
        for(Integer betAmount:betsSelected) {
            if (betAmount > currentPlayer.getScore()) {
                betAmount = currentGamemode.getMinBet();
            }
            currentGamemode.setBetAmount(betAmount, playerIndex);
            playerIndex++;
        }
    }
}
