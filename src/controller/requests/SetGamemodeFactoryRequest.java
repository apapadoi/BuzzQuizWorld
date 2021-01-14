package controller.requests;

import controller.Dispatcher;
import model.gamemodes.factories.GamemodeFactory;

/**
 * Represents a request for setting the gamemode factory for the current game session.
 * @author Tasos Papadopoulos
 * @version 14.1.2021
 */
public class SetGamemodeFactoryRequest extends Request{
    private final GamemodeFactory gamemodeFactory;

    /**
     * Creates a {@code SetGamemodeFactoryRequest} that will use the provided {@code GamemodeFactory} as a factory
     * for gamemodes for the current game session.
     * @param gamemodeFactory the new gamemode factory that will provide the gamemodes for the current game session
     */
    public SetGamemodeFactoryRequest(GamemodeFactory gamemodeFactory) {
        this.gamemodeFactory = gamemodeFactory;
    }

    /**
     * @see Request
     */
    @Override
    public void execute(Dispatcher dispatcher) {
        model.setGamemodeFactory(gamemodeFactory);
    }
}
