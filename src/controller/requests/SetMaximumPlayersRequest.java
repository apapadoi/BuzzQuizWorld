package controller.requests;

import controller.Dispatcher;

/**
 * Represents a request for setting the max number of players for the current game session.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class SetMaximumPlayersRequest extends Request{
    private final int maxPlayers;

    /**
     * Creates a {@code SetMaximumPlayersRequest} which will use the {@code maxPlayers} number provided for
     * setting the max players for the current game session.
     * @param maxPlayers the new number of max players for the current game session as {@code int}
     */
    public SetMaximumPlayersRequest(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.setMaxPlayers(this.maxPlayers);
    }
}
