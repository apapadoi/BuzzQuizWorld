package controller.requests;

import controller.Dispatcher;
import model.Model;

public class SetMaximumPlayersRequest extends Request{
    private final int maxPlayers;

    public SetMaximumPlayersRequest(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        Model.getInstance().setMaxPlayers(this.maxPlayers);
    }
}
