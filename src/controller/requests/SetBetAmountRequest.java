package controller.requests;

import controller.Dispatcher;
import model.Model;
import model.gamemodes.HighStakes;
import model.player.Player;
import model.round.Round;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class SetBetAmountRequest extends Request{
    private final ActionEvent e;

    public SetBetAmountRequest(ActionEvent e) {
        this.e = e;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model model = dispatcher.getModel();

        Round currentRound = model.getRound(Request.roundId);
        Player currentPlayer = model.getPlayers().get(0);
        HighStakes currentGamemode = (HighStakes)currentRound.getGamemode();

        int betAmount = Integer.parseInt(((JButton)e.getSource()).getText());
        if(betAmount > currentPlayer.getScore()) {
            betAmount = currentGamemode.getMinBet();
        }

        currentGamemode.setBetAmount(betAmount);
        dispatcher.getView().updateExtraJLabel("Bet : "+betAmount);
    }
}
