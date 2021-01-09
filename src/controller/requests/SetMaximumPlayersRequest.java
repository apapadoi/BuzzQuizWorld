package controller.requests;

import controller.Dispatcher;

public class SetMaximumPlayersRequest extends Request{
    private final int maxPlayers;

    public SetMaximumPlayersRequest(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void execute(Dispatcher dispatcher) {
        model.setMaxPlayers(this.maxPlayers);
    }
}
